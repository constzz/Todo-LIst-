package com.gmail.konstantin.bezzemelnyi.todolist.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.konstantin.bezzemelnyi.todolist.data.ToDoDatabase
import com.gmail.konstantin.bezzemelnyi.todolist.data.models.ToDoEntity
import com.gmail.konstantin.bezzemelnyi.todolist.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(app: Application) : AndroidViewModel(app) {
    private val toDoDao = ToDoDatabase.getInstance(app).toDoDao()
    private val repository: ToDoRepository = ToDoRepository(toDoDao)

    val getAllData = repository.getAllData

    fun insertData(toDoEntity: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoEntity)
        }
    }

    fun updateData(toDoEntity: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoEntity)
        }
    }

    fun deleteData(toDoEntity: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(toDoEntity)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}