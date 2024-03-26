package com.example.myapplication.models


import com.google.gson.annotations.SerializedName

data class TaskAddUpdateResponseModel(
    @SerializedName("message")
    var message: String?,
    @SerializedName("task")
    var task: TaskResponseItem?
)