package com.example.mymovie.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mymovie.navigation.MovieScreens
import com.example.mymovie.ui.theme.MyMovieTheme
import com.example.mymovie.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Preview(showBackground = true)
@Composable
fun HomeScreen(movie: List<Movie> = getMovies(), navController: NavController = rememberNavController()) {
    var showMenu by remember {
        mutableStateOf(false)
    }


    Scaffold(
        //topBar und TopAppBar sind selber composables -> deshalb geschwungene Klammern
        topBar = {
            TopAppBar(title = { Text(text = "") },
                actions = {

                    IconButton(onClick = { showMenu = !showMenu }) {
                        //moreVert ist das drei Punkte Icon
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "drop down")
                    }
                    //per default ist das dropDownMenu nicht ausgefahren
                    //onDismissRequest -> wenn das DropDownMenu wieder geschlossen wird-> welche Events sollen passieren
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {

                        Column() {

                            DropdownMenuItem(onClick = {  navController.navigate(route = MovieScreens.FavoriteScreen.name) },
                                modifier = Modifier.width(150.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.Favorite,
                                        contentDescription = "favorites",
                                        modifier = Modifier.padding(8.dp))
                                    Text(text = "Favorites")

                                }

                            }
                        }
                    }
                }
            )
        }
    ) {


        MainContent(movie = movie, navController = navController)
    }
}

@Composable
fun MainContent(
    movie: List<Movie> = getMovies(),
    // brauch ich zum navigieren
    navController: NavController = rememberNavController(),
)  {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){
        LazyColumn {
            items(movie){ movie ->
                MovieRow(movie)
                //callback wird auch der Funktion MovieRow übergeben
                { movieId->
                    navController.navigate(route = MovieScreens.DetailScreen.name+"/$movieId")
                }
            }
        }
    }
}