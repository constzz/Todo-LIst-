package com.gmail.konstantin.bezzemelnyi.todolist.data

import androidx.room.TypeConverter
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toString(string: String): Priority {
        return Priority.valueOf(string)
    }

}