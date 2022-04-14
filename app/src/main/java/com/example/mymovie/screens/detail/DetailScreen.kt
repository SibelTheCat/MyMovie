package com.example.mymovie.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovie.viewmodels.FavoriteMovieViewModel
import com.example.mymovie.widgets.HorizontalScrolllabelImageView
import com.example.mymovie.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549",
    viewModel: FavoriteMovieViewModel = viewModel(),
) {

    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.primary, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack() //go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                }

            }
        }
    ) {
        MainContent(movie, viewModel)
    }
}

@Composable
fun MainContent(movie: Movie, viewModel: FavoriteMovieViewModel = viewModel()) {
    Column {
        MovieRow(
            movie = movie,
            favMovies = viewModel.getAllFavMovies(),
            yesHeart = { movie -> viewModel.addFavMovie(movie) },
            noHeart = { movie -> viewModel.removeFavMovie(movie) },
            withOrWithoutHeart = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        HorizontalScrolllabelImageView(movie = movie)
    }
}

//möchte den movie mit einer bestimmten id zurückbekommen.
fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}