package com.example.pomodorotimer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pomodorotimer.TimerScreen
import com.example.pomodorotimer.TimerSettings
import com.example.pomodorotimer.TimerSummary


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoute.TimerScreen.route
    ) {
        composable(route = BottomNavRoute.TimerScreen.route) {
            TimerScreen()
        }
        composable(route = BottomNavRoute.TimerSettings.route) {
            TimerSettings()
        }
        composable(route = BottomNavRoute.Summary.route) {
            TimerSummary()
        }
    }
}