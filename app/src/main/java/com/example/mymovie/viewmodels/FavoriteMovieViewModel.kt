package com.example.mymovie.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.testapp.models.Movie


class FavoriteMovieViewModel : ViewModel() {

    private var favMovies = mutableStateListOf<Movie>()

    fun addFavMovie(movie: Movie){
        favMovies.add(movie)
    }

    fun removeFavMovie(movie: Movie){
        favMovies.remove(movie)
    }

    fun getAllFavMovies(): List<Movie>{
        return favMovies
    }

}