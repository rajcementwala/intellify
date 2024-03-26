package com.example.myapplication.api

import com.example.myapplication.models.TaskAddRequest
import com.example.myapplication.models.TaskAddUpdateResponseModel
import com.example.myapplication.models.TaskResponseModel
import com.example.myapplication.models.TaskUpdateRequest
import okhttp3.internal.concurrent.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskApiService {

    @GET("glitch-tasks")
    suspend fun getTasks():Response<TaskResponseModel>

    @POST("add-task")
    suspend fun addNewTask(@Body task: TaskAddRequest):Response<TaskAddUpdateResponseModel>

    @POST("add-task")
    suspend fun updateTask(@Body task: TaskUpdateRequest):Response<TaskAddUpdateResponseModel>
}