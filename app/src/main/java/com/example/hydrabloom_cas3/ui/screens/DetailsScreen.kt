package com.example.hydrabloom_cas3.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController

@Composable
fun DetailsScreen(navController: NavController, plantId: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Details of plant $plantId", modifier = Modifier.align(Alignment.Center))

        // Back navigation button
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }
}
