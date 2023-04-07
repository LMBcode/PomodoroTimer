package com.example.pomodorotimer.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavRoute(val route:String,val title:String,val icon : ImageVector ?= null ){
    object TimerScreen : BottomNavRoute("TimerScreen","Timer", icon = Icons.Filled.Timer)
    object TimerSettings : BottomNavRoute("TimerSettings", title = "Settings",icon = Icons.Filled.Settings)
    object Summary : BottomNavRoute("Summary", title = "Summary",icon = Icons.Filled.Book)

    object Splash : BottomNavRoute("Splash", title = "Splash")

}
