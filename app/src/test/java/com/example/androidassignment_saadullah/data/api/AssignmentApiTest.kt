package com.example.androidassignment_saadullah.data.api

import com.example.androidassignment_saadullah.constants.Constants
import com.example.androidassignment_saadullah.utills.AssignmentApiTestUtils
import com.example.androidassignment_saadullah.utills.AssignmentApiTestUtils.Companion.enqueueResponse
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class AssignmentApiTest {

   private val translatedValuesResponseFileName = "TranslatedValues.json"


    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private val assignmentApi by lazy {
        retrofit.create(AssignmentApi::class.java)
    }


    @Test
    fun `returns_success`() {

        enqueueResponse(mockWebServer,translatedValuesResponseFileName)
        runBlocking {
            val response = assignmentApi.getTranslatedValues()

            // valid endpoint assertion
            TestCase.assertEquals("/b/R7A1", mockWebServer.takeRequest().path)

            //valid response assertion
            TestCase.assertEquals(response.body(),AssignmentApiTestUtils.getTranslatedValuesTestObject(translatedValuesResponseFileName))

        }
    }

    @Test
    fun `returns_error_not_found`()
    {
        mockWebServer.enqueue(MockResponse().setResponseCode(Constants.NOT_FOUND))
        runBlocking {
            val response = assignmentApi.getTranslatedValues()

            TestCase.assertEquals("/b/R7A1", mockWebServer.takeRequest().path)
            assertThat(response.code()).isEqualTo(Constants.NOT_FOUND)
            assertThat(response.body()).isEqualTo(null)
        }
    }



    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


}