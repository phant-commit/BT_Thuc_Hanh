package com.example.myapplication.navigation

sealed class Screens(val route: String) {
    object Screen1 : Screens("screen1")
    object Screen2 : Screens("screen2")
    object Screen3 : Screens("screen3")
    object Screen4 : Screens("screen4")
    object Screen5 : Screens("screen5")
    object Screen6 : Screens("screen6")

    // MỚI: Thêm route cho Screen7 với một tham số cho task ID
    object Screen7 : Screens("screen7/{taskId}") {
        // Hàm hỗ trợ để tạo route hoàn chỉnh với một ID cụ thể
        fun createRoute(taskId: Int) = "screen7/$taskId"
    }
    object Screen8 : Screens("screen8")
}