package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.api.Resource
import com.example.myapplication.models.TaskAddRequest
import com.example.myapplication.models.TaskAddUpdateResponseModel
import com.example.myapplication.models.TaskResponseItem
import com.example.myapplication.models.TaskUpdateRequest
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource,private val  networkDataSource: NetworkDataSource) :TaskRepository,ModifyTasks{
    override suspend fun getTasks(): Resource<List<TaskResponseItem>> {
       val  localTask = localDataSource.getTasks()

       return if (localTask is Resource.Success && localTask.data?.isNotEmpty() == true){
            Log.e("Tasks","task is not Empty")

            return localTask
        }else{
            networkDataSource.getTasks().also {
                if (it is Resource.Success){
                    it.data?.forEach {task->
                        localDataSource.insertTask(task)
                    }
                }
            }
        }

    }

    override suspend fun addTask(addRequest: TaskAddRequest): Resource<TaskAddUpdateResponseModel> {
          return  networkDataSource.addTask(addRequest).also {
              if (it is Resource.Success){
                  it.data?.task?.let {task->
                      localDataSource.insertTask(task)
                  }
              }
          }
    }

    override suspend fun updateTask(updateRequest: TaskUpdateRequest): Resource<TaskAddUpdateResponseModel> {
        return  networkDataSource.updateTask(updateRequest).also {

            if (it is Resource.Success){
                it.data?.task?.let {task->
                    localDataSource.insertTask(task)
                }

            }
        }
    }




}