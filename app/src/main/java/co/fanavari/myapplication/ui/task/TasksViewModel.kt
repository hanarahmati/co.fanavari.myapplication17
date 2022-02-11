package co.fanavari.myapplication.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import co.fanavari.myapplication.data.task.PreferencesManager
import co.fanavari.myapplication.data.task.SortOrder
import co.fanavari.myapplication.data.task.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao,
    private val preferencesManager: PreferencesManager
): ViewModel() {


    //val tasks = taskDao.getTask().asLiveData()

    val searchQuery = MutableStateFlow("")
//    val sortOrder = MutableStateFlow(SortOrder.BY_DATE)
//    val hideCompleted =  MutableStateFlow(false)

    val preferencesFlow = preferencesManager.preferencesFlow
    /*private val tasksFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }*/

  /*  private val tasksFlow = combine(
        searchQuery, sortOrder, hideCompleted
    ){
        query,  sortOrder, hideCompleted ->
        Triple(query, sortOrder, hideCompleted)
    }.flatMapLatest { (query, sortOrder, hideCompleted) ->
        taskDao.getTasks(query, sortOrder, hideCompleted)
    }*/

    private val tasksFlow = combine(
        searchQuery,
        preferencesFlow
    ){
        query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query,  filterPreferences) ->
        taskDao.getTasks(query, filterPreferences.sortOrder, filterPreferences.hideCompleted)
    }


    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {

        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClicked(hideCompleted: Boolean) = viewModelScope.launch{

        preferencesManager.updateHideCompleted(hideCompleted)
    }

    val tasks = tasksFlow.asLiveData()
}

