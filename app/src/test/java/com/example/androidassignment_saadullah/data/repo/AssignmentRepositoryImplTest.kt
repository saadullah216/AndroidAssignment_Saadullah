package com.example.androidassignment_saadullah.data.repo

import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AssignmentRepositoryImplTest {

    private lateinit var assignmentRepository: FakeAssignmentRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        assignmentRepository = FakeAssignmentRepository()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `get_translated_values_success`() {

        val translatedValues = TranslatedValues(0.1f, 0.1f)
        mainCoroutineRule.runBlockingTest {
           val response = assignmentRepository.getTranslatedValues()
            assertThat(response.data).isEqualTo(translatedValues)
            assertThat(response.message).isEqualTo(null)
        }

    }

    @Test
    fun `get_translated_values_error`() {

        assignmentRepository.setShouldReturnError()
        mainCoroutineRule.runBlockingTest {
            val response = assignmentRepository.getTranslatedValues()
            assertThat(response.data).isEqualTo(null)
            assertThat(response.message).isEqualTo("network Error")
        }

    }
}