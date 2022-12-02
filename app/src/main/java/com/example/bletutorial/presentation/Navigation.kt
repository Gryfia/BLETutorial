package com.example.bletutorial.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(
    onBluetoothStateChanged:()->Unit,
    NIK: String = "",
    umur: Int = 0,
    context: Context
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route){
        composable(Screen.StartScreen.route){
            StartScreen(navController = navController)
        }

        composable(Screen.ScaleScreen.route){
            ScaleScreen(
                onBluetoothStateChanged, navController = navController, NIK = NIK, umur = umur, context = context
            )
        }
    }

}

sealed class Screen(val route:String){
    object StartScreen:Screen("start_screen")
    object ScaleScreen:Screen("scale_screen")
}