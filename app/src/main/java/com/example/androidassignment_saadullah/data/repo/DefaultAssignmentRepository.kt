package com.example.androidassignment_saadullah.data.repo

import com.example.androidassignment_saadullah.data.api.AssignmentApi
import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.Resource
import javax.inject.Inject

/**
 * main repository for making a network call and to parse data
 * used in view model to update live data
 * @param assignmentApi is injected with dagger hilt
 */
class DefaultAssignmentRepository @Inject constructor(
    private val assignmentApi: AssignmentApi
) : AssignmentRepository {
    override suspend fun getTranslatedValues(): Resource<TranslatedValues> {
        return try {
            val response = assignmentApi.getTranslatedValues()
            val result = response.body()
            println("saad" + response.body())
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
