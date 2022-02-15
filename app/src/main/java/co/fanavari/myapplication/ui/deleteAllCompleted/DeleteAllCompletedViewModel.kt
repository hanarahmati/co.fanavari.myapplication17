package co.fanavari.myapplication.ui.deleteAllCompleted

import androidx.lifecycle.ViewModel
import co.fanavari.myapplication.data.task.TaskDao
import co.fanavari.myapplication.di.ApplicationScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAllCompletedViewModel @Inject constructor(
    private val taskDao: TaskDao,
    @ApplicationScope private val applicationScope: CoroutineScope

): ViewModel() {
    fun onConfirmClick()  = applicationScope.launch {
        taskDao.deleteCompletedTasks()
    }
}