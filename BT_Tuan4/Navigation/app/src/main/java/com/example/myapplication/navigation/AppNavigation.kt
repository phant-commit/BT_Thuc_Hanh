package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.Screen1
import com.example.myapplication.screens.Screen2
import com.example.myapplication.screens.Screen3
import com.example.myapplication.screens.Screen4

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Screen1.route
    ) {
        composable(route = Screens.Screen1.route) {
            Screen1(navController)
        }
        composable(route = Screens.Screen2.route) {
            Screen2(navController)
        }
        composable(route = Screens.Screen3.route) {
            Screen3(navController)
        }
        composable(route = Screens.Screen4.route) {
            Screen4(navController)
        }
    }
}