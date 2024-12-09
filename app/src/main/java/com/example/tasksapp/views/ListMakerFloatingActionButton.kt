package com.example.tasksapp.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMakerFloatingActionButton(
    modifier: Modifier = Modifier,
    title: String,
    inputHint: String,
    onFabClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }

    FloatingActionButton(
        onClick = {
            showDialog = true
        },
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(title) },
            text = {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text(inputHint) },
                    singleLine = true
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onFabClick(taskName)
                        taskName = ""
                    },
                    content = {
                        Text("Create")
                    }
                )
            }

        )
    }
}