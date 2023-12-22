package com.example.nutriscans.homepage

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nutriscans.R
import com.example.nutriscans.navigation.NavigationItem
import com.example.nutriscans.navigation.NavScreen
import com.example.nutriscans.screen.HistoryScreen
import com.example.nutriscans.screen.HomeScreen
import com.example.nutriscans.screen.ScanScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavScreen.Home.route) {
                HomeScreen()
            }
            composable(NavScreen.History.route) {
                HistoryScreen()
            }
            composable(NavScreen.Scan.route) {
                ScanScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                icon = painterResource(id = R.drawable.home_icon),
                screen = NavScreen.Home
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.scan_icon),
                screen = NavScreen.Scan
            ),
            NavigationItem(
                icon = painterResource(id = R.drawable.history_icon),
                screen = NavScreen.History
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = ""
                    )
                },

                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

