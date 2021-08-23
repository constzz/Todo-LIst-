package com.gmail.konstantin.bezzemelnyi.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity


@Database(entities = [ToDoEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {

        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            if (INSTANCE != null) {
                return INSTANCE as ToDoDatabase
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}