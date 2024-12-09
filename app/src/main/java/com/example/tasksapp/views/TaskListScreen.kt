package com.example.tasksapp.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasksapp.data.TaskList
import com.example.tasksapp.viewmodel.ListDataManager

@Composable
fun TaskListScreen(
    modifier: Modifier = Modifier,
    navigate : (String) -> Unit
) {
    val taskListViewModel : ListDataManager = viewModel()
    val viewModelTasks = taskListViewModel.readLists().toList()
    var tasks by remember {
        mutableStateOf(viewModelTasks)
    }

    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = "topbar",
                showBackButton = false,
                onBackPressed = {}
            )
        },
        content = {
            TaskListContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                tasks = tasks,
                onClick = {taskName ->
                    navigate(taskName)
                }
            )
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = "Name of List",
                inputHint = "Task hint",
                onFabClick = {
                    tasks = (tasks + TaskList(it))
                    taskListViewModel.saveList(TaskList(it))
                    navigate(it)
                }
            )
        }
    )
}

@Composable
fun TaskListContent(
    modifier: Modifier = Modifier,
    tasks : List<TaskList>,
    onClick : (String) -> Unit
) {
    if(tasks.isEmpty()){
        EmptyView(message = "No tasks yet")
    }
    else{
        LazyColumn(
            modifier = modifier,
            content = {
                items(tasks){
                    ListItemView(
                        value = it.name,
                        onClick = onClick
                    )
                }
            }
        )
    }
}