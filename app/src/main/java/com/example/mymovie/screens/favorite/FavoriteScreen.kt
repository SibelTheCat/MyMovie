package com.example.mymovie.screens.favorite

import android.widget.ThemedSpinnerAdapter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovie.navigation.MovieScreens
import com.example.mymovie.viewmodels.FavoriteMovieViewModel
import com.example.mymovie.widgets.MovieRow
import com.example.testapp.models.Movie


@Preview(showBackground = true)
@Composable
fun FavouriteScreen(
    navController: NavController = rememberNavController(),
    viewModel: FavoriteMovieViewModel = viewModel(),
    favMovies: List<Movie> = viewModel.getAllFavMovies()){

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
                    Text(text = "My Favorite Movies")
                }

            }
        }
    ) {
        MainContent(viewModel, favMovies, navController)
    }
}
@Composable
fun MainContent (viewModel: FavoriteMovieViewModel = viewModel(), favMovies: List<Movie> = viewModel.getAllFavMovies(), navController: NavController= rememberNavController()) {
    LazyColumn {
        items(favMovies){ favMovie ->
            MovieRow(favMovie,viewModel.getAllFavMovies(),
                onItemClick = { movieId -> navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")},
                withOrWithoutHeart = false)

}}}


