package com.example.androidassignment_saadullah.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassignment_saadullah.data.repo.AssignmentRepository
import com.example.androidassignment_saadullah.data.repo.AssignmentRepositoryImpl
import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * main view model to get data from repo
 * @param assignmentRepository is injected
 */
@HiltViewModel
class MainViewModel @Inject constructor(
        private val assignmentRepository: AssignmentRepository
) : ViewModel() {


    /**
     * keeps state of data that will be observed by views
     */
    sealed class TranslatedValuesEvent {
        class Success(val translatedValues: TranslatedValues) : TranslatedValuesEvent()
        class Failure(val errorText: String) : TranslatedValuesEvent()
        object Loading : TranslatedValuesEvent()
        object Empty : TranslatedValuesEvent()
    }


    /**
     * mutable live data to keep the data provided by repository
     */
    private var _translatedValuesState = MutableLiveData<TranslatedValuesEvent>(TranslatedValuesEvent.Empty)

    /**
     * immutable live data that will be emitted towards views
     */
    val translatedValues: LiveData<TranslatedValuesEvent> = _translatedValuesState


    /**
     * method which is used only by view model to request data from repository
     */
    private fun fetchTranslatedValues() {
        viewModelScope.launch(Dispatchers.IO) {

            /**
             * current state is loading
             */
            _translatedValuesState?.postValue(TranslatedValuesEvent.Loading)

            /**
             * get data from repo and updating state according to response
             */
            when (val response = assignmentRepository.getTranslatedValues()) {
                is Resource.Error -> _translatedValuesState?.postValue(TranslatedValuesEvent.Failure(response.message!!))
                is Resource.Success -> {
                    val values = response.data!!
                    _translatedValuesState?.postValue(
                            TranslatedValuesEvent.Success(
                                    values
                            )
                    )
                }
            }

        }
    }

    /**
     * method which will be called by views
     * it makes sure that if wiew model dont have any data then network request is made
     */
    fun getTranslatedValesState() {
        if (translatedValues.value == TranslatedValuesEvent.Empty) {
            fetchTranslatedValues()
        }
    }
}