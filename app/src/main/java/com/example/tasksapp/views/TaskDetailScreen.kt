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
import com.example.tasksapp.navigation.Screens
import com.example.tasksapp.viewmodel.ListDataManager

@Composable
fun TaskDetailScreenContent(
    modifier: Modifier = Modifier,
    tasks : List<String>
) {
    if(tasks.isEmpty()){
        EmptyView(message = "No todos for this task yet")
    }else{
        LazyColumn(
            modifier = modifier,
            content = {
                items(tasks){
                    ListItemView(
                        value = it,
                        onClick = {}
                    )
                }
            }
        )
    }
}

@Composable
fun TaskDetailScreen(
    modifier: Modifier = Modifier,
    taskName : String?,
    onBackPressed : () -> Unit
) {
    val viewModel: ListDataManager = viewModel()
    var taskTodos by remember {
        mutableStateOf(viewModel.readLists().firstOrNull { it.name == taskName }?.tasks ?: emptyList())
    }

    Scaffold(
        topBar = {
            ListMakerTopAppBar(
                title = taskName ?: "Task",
                showBackButton = true,
                onBackPressed = onBackPressed
            )
        },
        content = {
            TaskDetailScreenContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                tasks = emptyList(),
            )
        },
        floatingActionButton = {
            ListMakerFloatingActionButton(
                title = "Name of List",
                inputHint = "Task hint",
                onFabClick = { todoName ->
                    viewModel.saveList(TaskList(taskName ?: "", taskTodos + listOf(todoName)))
                    taskTodos = viewModel.readLists().firstOrNull { it.name == taskName }?.tasks
                        ?: emptyList()
                }
            )
        }
    )
}