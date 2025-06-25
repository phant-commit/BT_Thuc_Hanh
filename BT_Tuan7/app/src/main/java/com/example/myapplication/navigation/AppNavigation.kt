// AppNavigation.kt
package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.screens.*
import com.example.myapplication.viewmodels.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(navController: NavHostController, taskViewModel: TaskViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Screen6.route // Màn hình bắt đầu là Screen6 (danh sách task)
    ) {
        // ... các composable cho Screen1 đến Screen5 không thay đổi ...
        composable(route = Screens.Screen1.route) { Screen1(navController = navController) }
        composable(route = Screens.Screen2.route) { Screen2(navController = navController) }
        composable(route = Screens.Screen3.route) { Screen3(navController = navController) }
        composable(route = Screens.Screen4.route) { Screen4(navController = navController) }
        composable(route = Screens.Screen5.route) { Screen5(navController = navController) }


        // Định nghĩa màn hình danh sách Task
        composable(route = Screens.Screen6.route) {
            Screen6(navController = navController, taskViewModel = taskViewModel)
        }

        // Định nghĩa màn hình chi tiết Task
        composable(
            route = Screens.Screen7.route, // Route mẫu: "screen7/{taskId}"
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId")
            if (taskId != null) {
                Screen7(
                    navController = navController,
                    taskViewModel = taskViewModel,
                    taskId = taskId
                )
            }
        }

        // SỬA ĐỔI: Thêm route cho Screen8 (Màn hình thêm Task mới)
        composable(route = Screens.Screen8.route) {
            val scope = rememberCoroutineScope() // Tạo coroutine scope để gọi hàm suspend

            Screen8( // Sử dụng Screen8 đã được định nghĩa
                onBackClick = {
                    navController.popBackStack() // Quay lại màn hình trước đó
                },
                onAddClick = { taskName, description ->
                    // Sử dụng coroutine để gọi hàm thêm task trong ViewModel
                    scope.launch {
                        // Giả sử taskViewModel.addTask trả về ID của task mới
                        // Bạn cần đảm bảo hàm này được triển khai trong ViewModel
                        val newTaskId = taskViewModel.addTask(taskName, description)

                        // Sau khi thêm thành công, điều hướng đến màn hình chi tiết (Screen7)
                        // và xóa Screen8 khỏi back stack để người dùng không thể quay lại.
                        navController.navigate(Screens.Screen7.createRoute(newTaskId)) {
                            popUpTo(Screens.Screen8.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
    }
}