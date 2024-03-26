package com.example.myapplication.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.interfaces.ItemSelectionListener
import com.example.myapplication.databinding.TasskListItemBinding
import com.example.myapplication.models.TaskResponseItem

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var list = mutableListOf<TaskResponseItem>()

    class ViewHolder(val binding: TasskListItemBinding) : RecyclerView.ViewHolder(binding.root)

     var itemSelectionListener: ItemSelectionListener?=null

    fun addListener(itemSelectionListener: ItemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener
    }

    fun loadTask(taskList: List<TaskResponseItem>) {
        this.list.clear()
        this.list.addAll(taskList)
        notifyDataSetChanged()
    }

    fun addOrUpdate(item:TaskResponseItem){
        val index = list.indexOfFirst { it.taskId==item.taskId }

        if (index>=0){
            list.set(index,item)
            notifyItemChanged(index)
        }
        else{
            list.add(0,item)
            notifyItemInserted(0)
        }

    }








    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TasskListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.taskItem= list[position]

            holder.binding.btnEdit.setOnClickListener {
                itemSelectionListener?.updateTask(list[position])
            }

            holder.binding.ivFav.setOnClickListener {
                itemSelectionListener?.addRemoveFav(list[position])
            }

    }
}