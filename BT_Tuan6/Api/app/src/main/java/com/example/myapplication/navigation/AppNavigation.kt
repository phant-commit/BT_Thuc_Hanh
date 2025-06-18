package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.screens.*
import com.example.myapplication.viewmodels.TaskViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, taskViewModel: TaskViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Screen1.route // Màn hình bắt đầu là Screen6 (danh sách task)
    ) {
        // Định nghĩa các màn hình khác (hiện tại là placeholder)
        composable(route = Screens.Screen1.route) {
            Screen1(navController = navController)
        }
        composable(route = Screens.Screen2.route) {
            Screen2(navController = navController)
        }
        composable(route = Screens.Screen3.route) {
            Screen3(navController = navController)
        }
        composable(route = Screens.Screen4.route) {
            Screen4(navController = navController)
        }
        composable(route = Screens.Screen5.route) {
            Screen5(navController = navController)
        }

        // Định nghĩa màn hình danh sách Task
        composable(route = Screens.Screen6.route) {
            Screen6(navController = navController, taskViewModel = taskViewModel)
        }

        // Định nghĩa màn hình chi tiết Task
        // Route này có một tham số là "taskId"
        composable(
            route = Screens.Screen7.route, // Route mẫu: "screen7/{taskId}"
            arguments = listOf(navArgument("taskId") {
                type = NavType.IntType // Kiểu dữ liệu của tham số là Int
            })
        ) { backStackEntry ->
            // Lấy taskId từ các tham số của route
            val taskId = backStackEntry.arguments?.getInt("taskId")

            // Đảm bảo taskId không null trước khi hiển thị màn hình
            if (taskId != null) {
                Screen7(
                    navController = navController,
                    taskViewModel = taskViewModel,
                    taskId = taskId
                )
            }
        }
    }
}