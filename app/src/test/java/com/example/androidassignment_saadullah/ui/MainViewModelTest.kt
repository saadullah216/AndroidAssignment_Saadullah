package com.example.androidassignment_saadullah.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidassignment_saadullah.data.repo.AssignmentRepository
import com.example.androidassignment_saadullah.data.repo.FakeAssignmentRepository
import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.MainCoroutineRule
import com.example.androidassignment_saadullah.utills.Resource
import com.example.androidassignment_saadullah.utills.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainViewModelTest {

    // mock dependency
   // private lateinit var fakeAssignmentRepository: AssignmentRepository
    private var fakeAssignmentRepository: AssignmentRepository = mock()
    private lateinit var mainViewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        mainViewModel = MainViewModel(fakeAssignmentRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `loading_state`() {

        mainCoroutineRule.runBlockingTest {

            mainViewModel.getTranslatedValesState()

            val value = mainViewModel.translatedValues.getOrAwaitValueTest()

            assertThat(value).isEqualTo(MainViewModel.TranslatedValuesEvent.Loading)
        }
    }

    @Test
    fun `success_state`() {
        val translatedValues = TranslatedValues(0.1f, 0.1f)
        mainCoroutineRule.runBlockingTest {

            mainViewModel.getTranslatedValesState()

            mainViewModel.translatedValues.observeForever {
                when (it) {
                    is MainViewModel.TranslatedValuesEvent.Success -> {
                        assertThat(it.translatedValues).isEqualTo(translatedValues)
                    }
                }
            }
        }
    }

    @Test
    fun `error_state`() {


        mainCoroutineRule.runBlockingTest {
            whenever(fakeAssignmentRepository.getTranslatedValues()).thenReturn( Resource.Error("error"))
            mainViewModel.getTranslatedValesState()

            mainViewModel.translatedValues.observeForever {
                when(it){
                    is MainViewModel.TranslatedValuesEvent.Failure ->{
                        assertThat(it.errorText).isEqualTo("error")
                    }
                }
            }
        }
    }
}