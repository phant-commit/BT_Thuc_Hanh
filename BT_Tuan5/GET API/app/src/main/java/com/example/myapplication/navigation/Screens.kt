package com.example.myapplication.navigation

sealed class Screens(val route: String) {
    object Screen1 : Screens("screen1")
    object Screen2 : Screens("screen2")
    object Screen3 : Screens("screen3")
    object Screen4 : Screens("screen4")
    object Screen5 : Screens("screen5")
    object Screen6 : Screens("screen6")

}