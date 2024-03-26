package com.example.myapplication.repository

import com.example.myapplication.api.Resource
import com.example.myapplication.models.TaskResponseItem

interface TaskRepository  {

   suspend fun getTasks(): Resource<List<TaskResponseItem>>

}