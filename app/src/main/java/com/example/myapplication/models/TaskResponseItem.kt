package com.example.myapplication.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.utils.Contstants.TASK_TABLE_NAME
import com.example.myapplication.utils.getReadableDate
import com.google.gson.annotations.SerializedName

@Entity(tableName = TASK_TABLE_NAME)
data class TaskResponseItem(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("task_id")
    var taskId: Int?,

    @SerializedName("created_date")
    var createdDate: String?,

    @SerializedName("is_favourite")
    var isFavourite: Boolean=false,

    @SerializedName("task_details")
    var taskDetails: String?,

    @SerializedName("task_name")
    var taskName: String?,

    @SerializedName("updated_date")
    var updatedDate: String?
){

    fun getFormattedCreateDate():String{
      return  createdDate?.getReadableDate()?:""
    }
    fun getFormattedUpdateDate():String{
        return  updatedDate?.getReadableDate()?:""

    }
}