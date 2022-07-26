package com.geancarloleiva.jetcommovieapp_11.navigation

enum class MovieScreens {
    HomeScreen,
    DetailScreen;

    companion object{
        fun fromRoute(route: String?): MovieScreens{
            when(route?.substringBefore("/")){
                HomeScreen.name -> return HomeScreen
                DetailScreen.name -> return DetailScreen
                null -> return HomeScreen
                else -> return throw IllegalArgumentException("Route $route is not correct")
            }
        }
    }
}