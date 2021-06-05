package com.gmail.konstantin.bezzemelnyi.todolist.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gmail.konstantin.bezzemelnyi.todolist.R
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.Priority
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity
import com.gmail.konstantin.bezzemelnyi.todolist.databinding.RowLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var dataList = emptyList<ToDoEntity>()

    class ViewHolder(var binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        with(holder) {
            binding.titleTxt.text = currentItem.title
            binding.descriptionTxt.text = currentItem.description
            binding.priorityIndicator.setPriorityIndicatorColor(currentItem.priority, binding.root.context)

            binding.rowBackground.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoEntity>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }

}

private fun CardView.setPriorityIndicatorColor(priority: Priority, ctx: Context) {
    this.setCardBackgroundColor(ContextCompat.getColor(context,
            when (priority) {
                Priority.HIGH -> R.color.red
                Priority.MEDIUM -> R.color.yellow
                Priority.LOW -> R.color.green
            }
    ))
}
