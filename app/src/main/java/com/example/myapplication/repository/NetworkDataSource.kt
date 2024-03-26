package com.example.myapplication.repository

import com.example.myapplication.api.Resource
import com.example.myapplication.api.TaskApiService
import com.example.myapplication.api.makeApiCall
import com.example.myapplication.models.TaskAddRequest
import com.example.myapplication.models.TaskAddUpdateResponseModel
import com.example.myapplication.models.TaskResponseItem
import com.example.myapplication.models.TaskUpdateRequest
import javax.inject.Inject

class NetworkDataSource @Inject constructor (private val taskApiService: TaskApiService)  :TaskRepository,ModifyTasks{
    override suspend fun getTasks(): Resource<List<TaskResponseItem>> {
        return makeApiCall { taskApiService.getTasks() }
    }

    override suspend fun addTask(addRequest: TaskAddRequest): Resource<TaskAddUpdateResponseModel> {
       return makeApiCall{taskApiService.addNewTask(addRequest)}
    }

    override suspend fun updateTask(updateRequest: TaskUpdateRequest): Resource<TaskAddUpdateResponseModel> {
        return makeApiCall{taskApiService.updateTask(updateRequest)}
    }

}