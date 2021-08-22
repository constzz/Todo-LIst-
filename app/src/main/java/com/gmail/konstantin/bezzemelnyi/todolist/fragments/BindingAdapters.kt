package com.gmail.konstantin.bezzemelnyi.todolist.fragments

import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.gmail.konstantin.bezzemelnyi.todolist.R
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.Priority
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity
import com.gmail.konstantin.bezzemelnyi.todolist.fragments.list.ListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object {

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)

                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
            when (emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:enablePrioritySpinner")
        @JvmStatic
        fun enablePrioritySpinner(view: Spinner, priority: Priority) {
            view.setSelection(
                when (priority) {
                    Priority.HIGH -> 0
                    Priority.MEDIUM -> 1
                    Priority.LOW -> 2
                }
            )
        }

        @BindingAdapter("android:backgroundTintOfPriority")
        @JvmStatic
        fun backgroundTintOfPriority(view: CardView, priority: Priority) {
            view.setCardBackgroundColor(
                ContextCompat.getColor(
                    view.context,
                    when (priority) {
                        Priority.HIGH -> R.color.red
                        Priority.MEDIUM -> R.color.yellow
                        Priority.LOW -> R.color.green
                    }
                )
            )
        }


        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: View, toDoEntity: ToDoEntity) {
            view.setOnClickListener {
                view.findNavController()
                    .navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(toDoEntity))
            }
        }

    }
}