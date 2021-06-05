package com.gmail.konstantin.bezzemelnyi.todolist.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "todo_table")
data class ToDoEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        var title: String,
        var priority: Priority,
        var description: String,
): Parcelable