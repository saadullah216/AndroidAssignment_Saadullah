package com.example.androidassignment_saadullah.repo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidassignment_saadullah.data.repo.AssignmentRepository
import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.FactorCalculator
import com.example.androidassignment_saadullah.utills.Resource
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AssignmentRepositoryTestImpl  {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var assignmentRepository : AssignmentRepository




//    @Before
//    fun setUp()
//    {
//        rule.inject(this)
//    }

    @Test
    fun getData() {
        runBlocking {
            val data = assignmentRepository.getTranslatedValues()
            if(data != null)
            {
                Truth.assertThat(true)
            }

        }
    }


}