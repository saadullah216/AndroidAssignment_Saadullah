package com.example.androidassignment_saadullah.di


import com.example.androidassignment_saadullah.constants.Constants
import com.example.androidassignment_saadullah.data.api.AssignmentApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * prepares retrofit object
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /**
     * prepares Gson to inject
     */
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    /**
     * prepares Retrofit builder to inject
     * @param gson is injected
     */
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson:  Gson ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    /**
     * prepares Retrofit to make network requests
     * @param retrofitBuilder is injected
     */
    @Singleton
    @Provides
    fun provideAssignmentApiService(retrofit: Retrofit.Builder): AssignmentApi {
        return retrofit
            .build()
            .create(AssignmentApi::class.java)
    }

}




















