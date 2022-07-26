package com.geancarloleiva.jetcommovieapp_11.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            //.height(130.dp) //the height should be dynamic when I expand or contract the "more details"
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
    var stateExpanded by remember {
        mutableStateOf(false)
    }

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
        //Additional info, by default hidden
        AnimatedVisibility(visible = stateExpanded) {
            Column {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 13.sp
                            )
                        ) {
                            append("Plot: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.DarkGray, fontSize = 13.sp,
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append(movie.plot)
                        }
                    }, modifier = Modifier.padding(6.dp)
                )
                Divider()
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Actors: ${movie.actors}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Rating: ${movie.rating}",
                    style = MaterialTheme.typography.caption
                )
            }
        }

        //Icon to see more info about the movie
        Icon(
            imageVector = if (stateExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "More info",
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    stateExpanded = !stateExpanded
                },
            tint = Color.DarkGray
        )
    }
}