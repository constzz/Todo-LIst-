package com.gmail.konstantin.bezzemelnyi.todolist.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity

class ToDoDiffUtil(
    private val oldList: List<ToDoEntity>,
    private val newList: List<ToDoEntity>,
) : DiffUtil.Callback(
) {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, oldTitle, oldPriority, oldDescription) = oldList[oldItemPosition]
        val (_, newTitle, newPriority, newDescription) = newList[newItemPosition]
        return oldTitle == newTitle
                && oldPriority == newPriority
                && oldDescription == newDescription
    }

}