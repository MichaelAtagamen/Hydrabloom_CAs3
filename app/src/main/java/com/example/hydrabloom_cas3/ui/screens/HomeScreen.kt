package com.example.hydrabloom_cas3.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hydrabloom_cas3.data.Plant
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun HomeScreen(navController: NavHostController) {
    var isLoggedIn by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        LoginScreen(onLoginSuccess = { isLoggedIn = true })
    } else {
        UserHomeScreen(navController = navController)
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = rememberImagePainter("https://plus.unsplash.com/premium_photo-1668096747228-c32252e8943f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGxhbnRzJTIwYmFja2dyb3VuZHxlbnwwfHwwfHx8MA%3D%3D"),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Semi-transparent overlay for text contrast
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)) // Darkens the background
        )

        // Column for text and button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text with improved visibility
            Text(
                text = "The World of Decorative Plants",
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White), // White text for contrast
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Take care of your houseplants, and before you know it,your home will be a botanical paradise.",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White), // White text for contrast
                modifier = Modifier.padding(bottom = 32.dp),
                lineHeight = 20.sp
            )
            // Button with white text for contrast
            Button(
                onClick = { onLoginSuccess() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green color
            ) {
                Text(text = "Sign in with Google", color = Color.White) // White text for contrast
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomeScreen(navController: NavHostController) {
    // Variable to hold the plant list
    var plants by remember { mutableStateOf(getSamplePlants()) }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("HydraBloom") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF4CAF50) // Green color for the Navbar
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Add plant logic
                val newPlant = Plant(id = (plants.size + 1), name = "New Plant", waterLevel = 80f, temperature = 22f)
                plants = plants + newPlant // Add new plant to the list
                Log.d("UserHomeScreen", "Plant Added: ${newPlant.name}")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Plant")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            items(plants) { plant ->
                ExpandablePlantCard(plant = plant, navController = navController)
            }
        }
    }
}

@Composable
fun ExpandablePlantCard(plant: Plant, navController: NavHostController) {
    var isExpanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var reminderSet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        // Background image behind the card
        Image(
            painter = rememberImagePainter("https://static.vecteezy.com/system/resources/previews/002/058/893/non_2x/exotic-tropical-plant-background-free-vector.jpg"), // Replace with your image URL
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // The Card on top of the background image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                    if (isExpanded) {
                        // Navigate to the details screen when expanded
                        navController.navigate("plantdetails/${plant.id}")
                    }
                },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = if (reminderSet) Color(0xFFE0F7FA) else Color.LightGray)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter("https://fyf.tac-cdn.net/images/products/small/P-346.jpg?auto=webp&quality=60&width=650"),
                    contentDescription = plant.name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 16.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Temperature: ${plant.temperature}°C")
                Text(text = "Water Level: ${plant.waterLevel}%")

                if (isExpanded) {
                    // Additional details about the plant
                    Text(text = "More information about ${plant.name}...")

                    // Show a label if a reminder is set
                    if (reminderSet) {
                        Text(text = "Reminder Set!", color = Color.Green)
                    }

                    Button(onClick = { showDialog = true }) {
                        Text("Set Maintenance Reminder")
                    }

                    // Show dialog for setting the reminder
                    if (showDialog) {
                        ReminderDialog(onDismiss = { showDialog = false }) { dateTime ->
                            Log.d("ExpandablePlantCard", "Reminder set for: $dateTime for ${plant.name}")
                            reminderSet = true
                            showDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReminderDialog(onDismiss: () -> Unit, onSetReminder: (String) -> Unit) {
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Set Maintenance Reminder") },
        text = {
            Column {
                TextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    label = { Text("Date (e.g., 2024-10-15)") }
                )
                TextField(
                    value = selectedTime,
                    onValueChange = { selectedTime = it },
                    label = { Text("Time (e.g., 14:00)") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val dateTime = "$selectedDate $selectedTime"
                onSetReminder(dateTime)
            }) {
                Text("Set Reminder")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

fun getSamplePlants(): List<Plant> {
    return listOf(
        Plant(id = 1, name = "Peperomia Houseplant", waterLevel = 85f, temperature = 23f),
        Plant(id = 2, name = "Fern", waterLevel = 70f, temperature = 22f),
        Plant(id = 3, name = "Orchid", waterLevel = 50f, temperature = 20f)
    )
}
