package com.example.nutriscans.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object Scan : NavScreen("scan")
    object History : NavScreen("history")
    object Login : NavScreen( "login")
    object Register : NavScreen("register")

}