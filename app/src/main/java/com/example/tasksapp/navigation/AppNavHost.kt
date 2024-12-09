package com.example.tasksapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasksapp.views.TaskDetailScreen
import com.example.tasksapp.views.TaskListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.TaskListScreen.route
    ) {
        composable(Screens.TaskListScreen.route){
            TaskListScreen(
                navigate = {taskListName ->
                    navController.navigate("${Screens.TaskDetailsScreen.route}/$taskListName")
                }
            )
        }
        composable(
            route = "${Screens.TaskDetailsScreen.route}/{taskListName}",
            arguments = listOf(navArgument("taskListName") { type = NavType.StringType })
        ) {
            TaskDetailScreen(
                taskName = it.arguments?.getString("taskListName"),
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}