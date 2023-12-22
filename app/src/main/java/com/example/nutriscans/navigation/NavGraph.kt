package com.example.nutriscans.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nutriscans.firstpage.LoginPage
import com.example.nutriscans.firstpage.RegisterPage
import com.example.nutriscans.homepage.HomePage

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Login.route
    ){
        composable(route = NavScreen.Login.route) {
            LoginPage(navController = navController)
        }
        composable(route = NavScreen.Register.route) {
            RegisterPage(navController = navController)
        }
        composable(route = NavScreen.Home.route) {
            HomePage()
        }
    }
}