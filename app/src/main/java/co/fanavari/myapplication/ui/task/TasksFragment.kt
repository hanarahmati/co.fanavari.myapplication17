package co.fanavari.myapplication.ui.task

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.fanavari.myapplication.R
import co.fanavari.myapplication.data.task.SortOrder
import co.fanavari.myapplication.databinding.FragmentTasksBinding
import co.fanavari.myapplication.util.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.nio.file.Files.find


@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private val viewModel: TasksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTasksBinding.bind(view)

        val taskAdapter = TasksAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }



        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_tasks, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            menu.findItem(R.id.action_hide_completed_tasks).isChecked =
                viewModel.preferencesFlow.first().hideCompleted
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {

            R.id.action_sort_by_name -> {

                //viewModel.sortOrder.value = SortOrder.BY_NAME

                viewModel.onSortOrderSelected(SortOrder.BY_NAME)
                true
            }

            R.id.action_sort_by_date_created -> {
               // viewModel.sortOrder.value = SortOrder.BY_DATE
                viewModel.onSortOrderSelected(SortOrder.BY_DATE)
                true
            }

            R.id.action_hide_completed_tasks -> {

                item.isChecked = !item.isChecked
               // viewModel.hideCompleted.value = item.isChecked
                viewModel.onHideCompletedClicked(item.isChecked)
                true
            }

            R.id.action_delete_all_completed_tasks -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}