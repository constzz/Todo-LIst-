package com.gmail.konstantin.bezzemelnyi.todolist.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gmail.konstantin.bezzemelnyi.todolist.R
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.Priority
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(toDoData: List<ToDoEntity>) {
        emptyDatabase.value = toDoData.isEmpty()
    }

    val listener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
        ) {
            when (position) {
                0 -> (parent?.getChildAt(0) as TextView).setColor(PriorityColor.RED)
                1 -> (parent?.getChildAt(0) as TextView).setColor(PriorityColor.YELLOW)
                2 -> (parent?.getChildAt(0) as TextView).setColor(PriorityColor.GREEN)
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }

    internal fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> error("The priority name in spinner not match to the any function argument.")
        }
    }

    internal fun userInputIsEmpty(title: String, description: String): Boolean {
        return (TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

}

enum class PriorityColor {
    RED,
    YELLOW,
    GREEN
}

fun TextView.setColor(priorityColor: PriorityColor) {
    when (priorityColor) {
        PriorityColor.RED -> this.setTextColor(ContextCompat.getColor(context, R.color.red))
        PriorityColor.YELLOW -> this.setTextColor(ContextCompat.getColor(context, R.color.yellow))
        PriorityColor.GREEN -> this.setTextColor(ContextCompat.getColor(context, R.color.green))
    }
}