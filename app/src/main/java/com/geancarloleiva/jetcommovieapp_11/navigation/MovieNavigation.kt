package com.geancarloleiva.jetcommovieapp_11.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.geancarloleiva.jetcommovieapp_11.screen.detail.DetailScreen
import com.geancarloleiva.jetcommovieapp_11.screen.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            //here we pass the Composable fun for desired screen
            HomeScreen(navController = navController)
        }
        composable(
            MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) { backStackEntry ->
            //here we pass the Composable fun for desired screen
            DetailScreen(navController = navController,
                backStackEntry.arguments?.getString("movie"))
        }
    }
}