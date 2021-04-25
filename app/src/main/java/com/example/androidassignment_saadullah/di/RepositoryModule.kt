package com.example.androidassignment_saadullah.di

import com.example.androidassignment_saadullah.data.api.AssignmentApi
import com.example.androidassignment_saadullah.data.repo.AssignmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * preparation of repository object to inject in view model
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * creates object of assignment repo
     * @param assignmentApi injected from retrofit module
     */
    @Singleton
    @Provides
    fun provideAssignmentRepository(
        assignmentApi: AssignmentApi
    ): AssignmentRepository {
        return AssignmentRepository(assignmentApi)
    }
}