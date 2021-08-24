package com.gmail.konstantin.bezzemelnyi.todolist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoEntity>>

    @Insert
    suspend fun insertData(toDoEntity: ToDoEntity)

    @Update
    suspend fun updateData(toDoEntity: ToDoEntity)

    @Delete
    suspend fun deleteData(toDoEntity: ToDoEntity)

    @Query("DELETE FROM todo_table ")
    suspend fun deleteAll() 

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun findBySearchQuery(searchQuery: String): LiveData<List<ToDoEntity>>
}