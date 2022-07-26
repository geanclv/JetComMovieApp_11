package com.geancarloleiva.jetcommovieapp_11.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geancarloleiva.jetcommovieapp_11.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 5.dp
        ) {
            Text(text = "Movie App")
        }
    }) {
        MainContent(navController)
    }
}

@Composable
fun MainContent(navController: NavController,
    lstMovie: List<String> = listOf("300", "200", "200", "200", "200", "200", "200", "200")) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = lstMovie) {
                MovieRow(movie = it){ movie ->
                    navController.navigate(
                        route = MovieScreens.DetailScreen.name+"/$movie")
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick(movie)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Icon(imageVector = Icons.Default.Home, contentDescription = movie)
            }
            Text(text = movie)
        }
    }
}