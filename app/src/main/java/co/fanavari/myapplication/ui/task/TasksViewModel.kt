package co.fanavari.myapplication.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import co.fanavari.myapplication.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel(){

    val tasks = taskDao.getTask().asLiveData()
}