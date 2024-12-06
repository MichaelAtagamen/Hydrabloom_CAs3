package com.example.hydrabloom_cas3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.compose.rememberNavController
import com.example.hydrabloom_cas3.ui.theme.HydraBloomTheme
import com.example.hydrabloom_cas3.navigation.NavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Log app lifecycle
        Log.d("MainActivity", "App started!")

        setContent {
            HydraBloomTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController) // Use NavGraph for navigation
            }
        }
    }
}
