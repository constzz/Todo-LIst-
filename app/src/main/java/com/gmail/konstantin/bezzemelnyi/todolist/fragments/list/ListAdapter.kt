package com.gmail.konstantin.bezzemelnyi.todolist.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity
import com.gmail.konstantin.bezzemelnyi.todolist.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var dataList = emptyList<ToDoEntity>()

    class ViewHolder(var binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoEntity: ToDoEntity) {
            binding.todoEntity = toDoEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    RowLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoEntity>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}


