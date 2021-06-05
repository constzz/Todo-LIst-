package com.gmail.konstantin.bezzemelnyi.todolist.data.repository

import androidx.lifecycle.LiveData
import com.gmail.konstantin.bezzemelnyi.todolist.data.ToDoDao
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity

class ToDoRepository(val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoEntity>> = toDoDao.getAllData()

    suspend fun insertData(toDoEntity: ToDoEntity) {
        toDoDao.insertData(toDoEntity)
    }

    suspend fun updateData(toDoEntity: ToDoEntity) {
        toDoDao.updateData(toDoEntity)
    }

    suspend fun deleteData(toDoEntity: ToDoEntity) {
        toDoDao.deleteData(toDoEntity)
    }

    suspend fun deleteAll() {
        toDoDao.deleteAll()
    }

}