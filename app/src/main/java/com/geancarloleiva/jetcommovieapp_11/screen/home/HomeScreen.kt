package com.geancarloleiva.jetcommovieapp_11.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.geancarloleiva.jetcommovieapp_11.model.Movie
import com.geancarloleiva.jetcommovieapp_11.model.getMovies
import com.geancarloleiva.jetcommovieapp_11.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
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
fun MainContent(
    navController: NavController,
    lstMovie: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = lstMovie) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(
                        route = MovieScreens.DetailScreen.name + "/$movie"
                    )
                }
            }
        }
    }
}

@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            MovieDetail(movie = movie)
        }
    }
}

@Composable
fun MovieDetail(movie: Movie) {
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .size(100.dp),
        shape = RectangleShape,
        elevation = 4.dp
    ) {
        Image(
            //Only loading the image
            //painter = rememberAsyncImagePainter(model = movie.images[0]),
            //Loading with a crossfade during loading
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.images[0])
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build()
            ),
            contentDescription = movie.title
        )
    }
    Column(modifier = Modifier.padding(4.dp)) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Director: ${movie.director}",
            style = MaterialTheme.typography.caption
        )
        Text(
            text = "Release: ${movie.year}",
            style = MaterialTheme.typography.caption
        )
    }
}