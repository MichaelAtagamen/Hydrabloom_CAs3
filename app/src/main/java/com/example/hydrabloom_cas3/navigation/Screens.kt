package com.example.hydrabloom_cas3.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Details : Screens("details")
}


