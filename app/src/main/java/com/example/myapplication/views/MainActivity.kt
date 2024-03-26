package com.example.myapplication.views

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.myapplication.interfaces.ItemSelectionListener
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.TaskDialogBinding
import com.example.myapplication.models.TaskAddRequest
import com.example.myapplication.models.TaskResponseItem
import com.example.myapplication.models.TaskUpdateRequest
import com.example.myapplication.api.Resource
import com.example.myapplication.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private var dialog:Dialog?=null;
    lateinit var adapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        setObservers()
        init()
    }

    private fun init(){
        binding.fab.setOnClickListener {
            addOrUpdateTaskTask()
        }
         adapter=TaskAdapter()
        adapter.addListener(object : ItemSelectionListener {
            override fun updateTask(task: TaskResponseItem) {
               addOrUpdateTaskTask(task)
            }

            override fun addRemoveFav(task: TaskResponseItem) {
                viewModel.updateTask(TaskUpdateRequest(task_id = task.taskId?:0, task_name = task.taskName?:"", is_favourite = !task.isFavourite ,task_details = task.taskDetails?:""))
            }
        })
        binding.rvTasks.adapter=adapter


    }

    private fun setObservers() {
        viewModel.tasks.observe(this){
            when(it){
                is Resource.Success->{
                    hideProgress()
                    it.data?.let {data->
                        adapter.loadTask(data)
                    }

                }

                is Resource.Error->{
                    hideProgress()
                }

                is Resource.Loading->{
                    showProgress()
                }

                is Resource.APIException->{
                    hideProgress()
                }
            }
        }

        viewModel.addUpdateTaskData.observe(this){
            when(it){
                is Resource.Success->{
                    dialog?.dismiss()
                    hideProgress()
                    viewModel.getTasks()
                }

                is Resource.Error->{
                    hideProgress()
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }

                is Resource.Loading->{
                    showProgress()
                }

                is Resource.APIException->{
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    hideProgress()
                }
            }
        }
    }


    fun addOrUpdateTaskTask( task: TaskResponseItem?=null){
        var dialog= Dialog(this)
        val addTaskBinding= TaskDialogBinding.inflate(LayoutInflater.from(this))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(addTaskBinding.root);
        dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        if (task!=null){
            addTaskBinding.etDetails.setText(task.taskDetails)
            addTaskBinding.etTitle.setText(task.taskName)
        }

        addTaskBinding.btnSave.setOnClickListener {
            if (task!=null){
                val request = TaskUpdateRequest(task_id = task.taskId?:0, task_name = addTaskBinding.etTitle.text.toString(), is_favourite = task.isFavourite ,task_details = addTaskBinding.etDetails.text.toString())
                viewModel.updateTask(request)
            }else{
                val request = TaskAddRequest(task_name = addTaskBinding.etTitle.text.toString(), task_details = addTaskBinding.etDetails.text.toString())
                viewModel.addNewTask(request)
            }
        }
        this.dialog=dialog
        dialog.show()
    }


    private fun showProgress(){
        binding.progress.visibility=View.VISIBLE
    }
    private fun hideProgress(){
        binding.progress.visibility=View.GONE

    }


}