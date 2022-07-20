package com.geancarloleiva.jetcommovieapp_11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.geancarloleiva.jetcommovieapp_11.ui.theme.JetComMovieApp_11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MovieApp(content: @Composable () -> Unit) {
    JetComMovieApp_11Theme {
        Scaffold(topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 5.dp
            ) {
                Text(text = "Movie App")
            }
        }) {
            content
        }
    }
}

@Composable
fun MainContent() {

}