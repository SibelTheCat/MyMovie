package com.example.mymovie

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymovie.navigation.MovieNavigation
import com.example.mymovie.screens.home.HomeScreen
import com.example.mymovie.ui.theme.MyMovieTheme
import com.example.mymovie.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies
import kotlinx.coroutines.channels.ticker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                //HomeScreen(movie = getMovies())
                MovieNavigation()  // HomeScreen() --> Detailscreen
            }

          /*  MyMovieTheme {

                // A surface container using the 'background' color from the theme

                MainContent(movie = getMovies())

            }*/
        }
    }


@Composable

    fun MyApp(content: @Composable ()  -> Unit){


    MyMovieTheme {
        // A surface container using the 'background' color from the theme
        //observable Variable -> hier ein boolean value

             content()


    }}









                @Preview(showBackground = true)
                @Composable
                fun DefaultPreview() {
                    MyMovieTheme {
                        HomeScreen(movie = getMovies())

                    }
                }}
