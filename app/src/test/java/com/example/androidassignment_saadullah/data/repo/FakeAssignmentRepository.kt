package com.example.androidassignment_saadullah.data.repo

import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.Resource

class FakeAssignmentRepository : AssignmentRepository {

    var translatedValues = TranslatedValues(0.1f, 0.1f)

    private var shouldReturnError = false

    override suspend fun getTranslatedValues(): Resource<TranslatedValues> {
        if (shouldReturnError)
            return Resource.Error("network Error")
        else
            return Resource.Success(translatedValues)
    }

    fun setShouldReturnError() {
        shouldReturnError = true
    }
}