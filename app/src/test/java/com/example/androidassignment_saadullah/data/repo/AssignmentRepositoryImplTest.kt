package com.example.androidassignment_saadullah.data.repo

import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class AssignmentRepositoryImplTest {

    private lateinit var assignmentRepository: FakeAssignmentRepository

    @Before
    fun setUp() {
        assignmentRepository = FakeAssignmentRepository()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getTranslatedValues() {

        var translatedValues = TranslatedValues(0.1f, 0.1f)
        runBlocking {
           var response = assignmentRepository.getTranslatedValues()
            assertThat(response.data).isEqualTo(translatedValues)
            assertThat(response.message).isEqualTo(null)
        }

    }
}