package com.example.tasksapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    message : String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "list",
                modifier = Modifier.size(100.dp)
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(message, style = MaterialTheme.typography.titleMedium)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun EmptyViewPreview() {
    EmptyView(message = "No task yet")
}