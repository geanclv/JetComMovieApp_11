package com.geancarloleiva.jetcommovieapp_11.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geancarloleiva.jetcommovieapp_11.screen.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name){
            //here we pass the Composable fun for desired screen
            HomeScreen(navController = navController)
        }
        composable(MovieScreens.DetailScreen.name){
            //here we pass the Composable fun for desired screen
        }
    }
}