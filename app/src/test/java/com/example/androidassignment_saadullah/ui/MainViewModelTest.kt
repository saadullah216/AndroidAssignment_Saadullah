package com.example.androidassignment_saadullah.ui

import com.example.androidassignment_saadullah.data.repo.AssignmentRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class MainViewModelTest {

    // mock dependency
    private val fakeAssignmentRepository : AssignmentRepository = mock()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(fakeAssignmentRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getTranslatedValues() {



    }
}