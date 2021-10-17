package com.example.androidassignment_saadullah.utills


import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.io.File

class AssignmentApiTestUtils {

    companion object {

        fun getJson(path: String): String {
            val uri = this.javaClass.classLoader.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }

        fun getTranslatedValuesTestObject(path: String): TranslatedValues {
            val gson = Gson()
            return gson.fromJson(
                getJson(path),
                TranslatedValues::class.java
            )

        }

        fun enqueueResponse(
            mockWebServer: MockWebServer,
            filename: String,
            headers: Map<String, String> = emptyMap()
        ) {
            val inputStream = this.javaClass.classLoader!!.getResourceAsStream(filename)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()


            for ((key, value) in headers)
                mockResponse.addHeader(key, value)

            mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))

        }

    }

}