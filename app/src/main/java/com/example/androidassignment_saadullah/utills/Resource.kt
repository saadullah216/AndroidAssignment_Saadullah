package com.example.androidassignment_saadullah.utills


/**
 * helper class for error handling
 */
sealed class Resource<T>(val data: T?, val message: String?) {
    // in case of success of network call
    class Success<T>(data: T) : Resource<T>(data, null)
    // in case of any error of network call
    class Error<T>(message: String) : Resource<T>(null, message)
}