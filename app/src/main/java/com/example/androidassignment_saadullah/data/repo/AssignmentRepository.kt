package com.example.androidassignment_saadullah.data.repo

import com.example.androidassignment_saadullah.data.response.TranslatedValues
import com.example.androidassignment_saadullah.utills.Resource

interface AssignmentRepository {

    suspend fun getTranslatedValues(): Resource<TranslatedValues>
}