package com.example.hydrabloom_cas3.data

data class Plant(
    val id: Int,
    val name: String,
    val waterLevel: Float, // Percentage (e.g., 80.5f)
    val temperature: Float // In Celsius (e.g., 22.0f)
)
