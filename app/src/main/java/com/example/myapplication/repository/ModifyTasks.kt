package com.example.myapplication.repository

import com.example.myapplication.api.Resource
import com.example.myapplication.models.TaskAddRequest
import com.example.myapplication.models.TaskAddUpdateResponseModel
import com.example.myapplication.models.TaskUpdateRequest

interface ModifyTasks {

    suspend fun addTask(addRequest: TaskAddRequest): Resource<TaskAddUpdateResponseModel>
    suspend fun updateTask(updateRequest: TaskUpdateRequest): Resource<TaskAddUpdateResponseModel>

}