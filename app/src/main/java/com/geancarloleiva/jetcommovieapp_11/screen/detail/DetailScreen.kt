package com.geancarloleiva.jetcommovieapp_11.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geancarloleiva.jetcommovieapp_11.navigation.MovieScreens

@Composable
fun DetailScreen(
    navController: NavController,
    movieData: String?
) {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 5.dp
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Movie: $movieData")
            }
        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movieData.toString(),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(23.dp))
                Button(onClick = {
                    navController.popBackStack()
                }) {
                    Text(text = "Go Back")
                }
            }
        }
    }
}