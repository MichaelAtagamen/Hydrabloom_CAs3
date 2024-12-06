package com.example.hydrabloom_cas3.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hydrabloom_cas3.ui.screens.HomeScreen
import com.example.hydrabloom_cas3.ui.screens.DetailsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("plantdetails/{plantId}") { backStackEntry ->
            DetailsScreen(navController, plantId = backStackEntry.arguments?.getString("plantId") ?: "")
        }
    }
}
