package com.example.myapplication.api

import retrofit2.Response

suspend fun <T> makeApiCall(apiCall:suspend ()->Response<T>): Resource<T> {
    return try {



       val response =  apiCall.invoke()
        if (response.isSuccessful){
            val body = response.body()
            if (body!=null){
                return Resource.Success("Success",body)
            }
            else{
                return Resource.Error("Something went wrong")
            }

        }else{
            return Resource.Error("Something went wrong")
        }

    }catch (exception:Exception){
        Resource.APIException(exception.localizedMessage)
    }


}