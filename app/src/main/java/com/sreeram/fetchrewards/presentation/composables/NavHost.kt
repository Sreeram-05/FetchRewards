package com.sreeram.fetchrewards.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("mainScreen") { MainScreen(navController) }
        composable("sortTheResultsScreen") { SortedItemsScreen(navController) }
        composable("filterOutAnyItemsScreen") { FilteredItemsScreen(navController) }
        composable("list/{listId}") { backStackEntry ->
            val listId = backStackEntry.arguments?.getString("listId")?.toInt()
            ListDetailScreen(listId = listId, navController)
        }
    }
}