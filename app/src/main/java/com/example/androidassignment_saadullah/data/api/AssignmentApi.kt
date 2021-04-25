package com.example.androidassignment_saadullah.data.api

import com.example.androidassignment_saadullah.data.response.TranslatedValues
import retrofit2.Response
import retrofit2.http.GET

/**
 * interface for all network requests
 */
interface AssignmentApi {

    /**
     * http get request to get translated values
     */
    @GET("b/R7A1")
    suspend fun getTranslatedValues(): Response<TranslatedValues>
}


