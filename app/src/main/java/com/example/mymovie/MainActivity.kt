package com.example.mymovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.mymovie.navigation.MovieNavigation
import com.example.mymovie.ui.theme.MyMovieTheme
import com.example.mymovie.widgets.MovieRow
import com.example.testapp.models.getMovies

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
                 //   MovieRow(getMovies()[0], true)
                }
                }
