package com.gmail.konstantin.bezzemelnyi.todolist.common.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gmail.konstantin.bezzemelnyi.todolist.common.callback.ItemSwipeToDeleteCallback

fun RecyclerView.swipeToDeleteForItems(deleteMethod: (viewHolderPosition: Int)-> Unit) {
    ItemTouchHelper(object : ItemSwipeToDeleteCallback() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            deleteMethod(viewHolder.adapterPosition)
        }
    }).also {
        it.attachToRecyclerView(this)
    }
}