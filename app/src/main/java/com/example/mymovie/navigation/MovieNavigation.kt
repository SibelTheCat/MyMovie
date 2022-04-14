package com.example.mymovie.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mymovie.screens.detail.DetailScreen
import com.example.mymovie.screens.favorite.FavouriteScreen
import com.example.mymovie.screens.home.HomeScreen
import com.example.mymovie.viewmodels.FavoriteMovieViewModel
import com.example.testapp.models.getMovies

@Composable
fun MovieNavigation(){
    //navcontroller Instanz wird erstellt. ist stateful
    val navController = rememberNavController()
    val movieViewModel: FavoriteMovieViewModel = viewModel()

    //Nav host ist der container
    //nac Controller Instanz und Start Destination wird übergeben
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){

        //Navigation Graph
        //in der Klammer wird festgelegt wie die unique route heissen
        composable(route = MovieScreens.HomeScreen.name) { HomeScreen(movie = getMovies(), navController = navController, viewModel = movieViewModel)}

        //Url www.domain.at/detailscreen/id=12345
        //weitere Parameter können mit einem weiteren /{weiteres Argument} übergeben werden
        //um diese dann zu extrahieren: beim listOf mit , ein (navArgument() anfügen
        composable(route = MovieScreens.DetailScreen.name+"/{movie}",   //pfad mit movie id
            //name gibt an wo uns das argument übergeben wird
        arguments = listOf(navArgument("movie"){
            type = NavType.StringType                 //Argumente werden dem Screen übergeben im Typ String
        })
        //hier werden die Argument von dem Stack zuvor (also unserem vorherigen screen) gesoeichert
        //über backStackEntry.orguments können wir auf diese Argumente zugreifen
        //das ? sagt, dass wir nur auf diese Argmuene zugreifen wennn arguments nicht null sind

        //WICHTIG beim Aufruf darauf achten, dass ja die Destination sich jetzt geändert hat
        ){ backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movie"),viewModel = movieViewModel )
        }
        composable(route = MovieScreens.FavoriteScreen.name) { FavouriteScreen( navController = navController, viewModel = movieViewModel, favMovies = movieViewModel.getAllFavMovies() ) }

    }
    }
