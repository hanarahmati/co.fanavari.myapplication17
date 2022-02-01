package co.fanavari.myapplication.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.fanavari.myapplication.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private val viewModel: TasksViewModel by viewModels()
}