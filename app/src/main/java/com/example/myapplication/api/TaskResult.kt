package com.example.myapplication.api


sealed class Resource<out T> {
    data class Success<out T>(val message: String?, val data: T?) : Resource<T>()
    data class Error( val message: String?) : Resource<Nothing>()
    data class APIException(val message: String?) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()

}