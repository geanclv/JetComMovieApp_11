package com.geancarloleiva.jetcommovieapp_11.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.geancarloleiva.jetcommovieapp_11.model.Movie
import com.geancarloleiva.jetcommovieapp_11.model.getMovies
import com.geancarloleiva.jetcommovieapp_11.screen.home.MovieRow

@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String?
) {
    val lstMovie = getMovies().filter {
        it.id == movieId
    }
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
                Text(text = "Movie: $movieId")
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
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                MovieRow(movie = lstMovie[0], onItemClick = {})
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie images", modifier = Modifier.padding(top = 8.dp))
                HorizontalImageScroll(lstMovie[0])
            }
        }
    }
}

@Composable
fun HorizontalImageScroll(movie: Movie){
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = movie.title)
            }
        }
    }
}