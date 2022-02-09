package co.fanavari.myapplication.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.room.Query
import co.fanavari.myapplication.data.task.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {

    //val tasks = taskDao.getTask().asLiveData()

    val searchQuery = MutableStateFlow("")
    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
    val hideCompleted =  MutableStateFlow(false)

    /*private val tasksFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }*/

    private val tasksFlow = combine(
        searchQuery, sortOrder, hideCompleted
    ){
        query,  sortOrder, hideCompleted ->
        Triple(query, sortOrder, hideCompleted)
    }.flatMapLatest { (query, sortOrder, hideCompleted) ->
        taskDao.getTasks(query, sortOrder, hideCompleted)
    }

    val tasks = tasksFlow.asLiveData()
}

enum class SortOrder { BY_NAME, BY_DATE}