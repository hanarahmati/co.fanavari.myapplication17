package co.fanavari.myapplication.ui.task

import androidx.lifecycle.ViewModel
import co.fanavari.myapplication.data.TaskDao
import dagger.hilt.android.scopes.ViewModelScoped


class TasksViewModel @ViewModelScoped constructor(
    private val taskDao: TaskDao
): ViewModel(){
}