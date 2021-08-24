package com.gmail.konstantin.bezzemelnyi.todolist.common.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class ItemSwipeToDeleteCallback() : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.START + ItemTouchHelper.END
) {
    final override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    abstract override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
}