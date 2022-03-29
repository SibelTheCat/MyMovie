package com.example.mymovie.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovie.widgets.HorizontalScrolllabelImageView
import com.example.mymovie.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549") {

    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack() //go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = movie.title)
                }

            }
        }
    ) {
        MainContent(movie)
    }
}

@Composable
fun MainContent(movie: Movie){
Column {
    MovieRow(movie = movie)
    Text(text = movie.title, style = MaterialTheme.typography.h5)
    Spacer(modifier = Modifier.height(8.dp))
    Divider()
    HorizontalScrolllabelImageView(movie = movie)


}
}

//möchte den movie mit einer bestimmten id zurückbekommen.
fun filterMovie(movieId: String?): Movie {
    return getMovies().filter{ movie -> movie.id ==movieId}[0]
}