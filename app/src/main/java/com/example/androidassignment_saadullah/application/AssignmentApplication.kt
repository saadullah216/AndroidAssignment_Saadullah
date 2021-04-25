package com.example.androidassignment_saadullah.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * application class for project
 */
@HiltAndroidApp
class AssignmentApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}