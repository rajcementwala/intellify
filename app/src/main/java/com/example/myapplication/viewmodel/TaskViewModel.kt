package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.TaskAddRequest
import com.example.myapplication.models.TaskAddUpdateResponseModel
import com.example.myapplication.models.TaskResponseItem
import com.example.myapplication.models.TaskUpdateRequest
import com.example.myapplication.api.Resource
import com.example.myapplication.repository.TaskRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository:TaskRepositoryImpl) :ViewModel() {
    private val _tasks = MutableLiveData<Resource<List<TaskResponseItem>>>()
    val tasks: LiveData<Resource<List<TaskResponseItem>>> get() = _tasks

     private val _addUpdateTaskData = MutableLiveData<Resource<TaskAddUpdateResponseModel>>()
    val addUpdateTaskData: LiveData<Resource<TaskAddUpdateResponseModel>> get() = _addUpdateTaskData

    init {
        getTasks()
    }

    fun getTasks(){
        _addUpdateTaskData.value= Resource.Loading
        viewModelScope.launch {
            _tasks.value= repository.getTasks()
        }

    }

    fun addNewTask(addTaskAddRequest: TaskAddRequest){
        _addUpdateTaskData.value= Resource.Loading
        viewModelScope.launch {
            _addUpdateTaskData.value= repository.addTask(addTaskAddRequest)

        }

    }
    fun updateTask(updateTaskAddRequest: TaskUpdateRequest){
        _addUpdateTaskData.value= Resource.Loading
       viewModelScope.launch {
           _addUpdateTaskData.value= repository.updateTask(updateTaskAddRequest)
       }
    }


}