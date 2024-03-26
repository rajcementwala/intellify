package com.example.myapplication.repository

import com.example.myapplication.api.Resource
import com.example.myapplication.db.TaskDao
import com.example.myapplication.models.TaskResponseItem
import javax.inject.Inject

class LocalDataSource @Inject constructor (private val dao: TaskDao) :TaskRepository,SyncRepository{
    override suspend fun getTasks(): Resource<List<TaskResponseItem>> {
           val tasks= dao.getAllTask()
            return tasks?.let {
                return Resource.Success("success",it) }?: Resource.Success("",listOf<TaskResponseItem>())
    }

    override suspend fun insertTask(task: TaskResponseItem) {
       dao.insert(task)
    }


}