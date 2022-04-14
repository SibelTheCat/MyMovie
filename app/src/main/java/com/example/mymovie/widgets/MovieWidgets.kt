package com.example.mymovie.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    favMovies: List<Movie> = listOf(),
             //CallBack Function kann als lambda übergeben werden
    onItemClick: (String)-> Unit = {},
    yesHeart: (Movie) -> Unit = {},
    noHeart: (Movie) -> Unit = {},
    withOrWithoutHeart: Boolean
   // favorite: (Movie, (Movie) -> Unit, (Movie) -> Unit) -> Unit = { movie: Movie, function: (Movie) -> Unit, function1: (Movie) -> Unit -> },
    ){
    var upDown by remember {

        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            //damit wenn Textfeld ausgefahren wird die Card größer ist
            // .animateContentSize()

            //wenn auf die Card geklickt wird, wird die übergebene Funkton "onItemClick"
            // mit der movie id auf des geklckten Films aufgerufen
            .clickable { onItemClick(movie.id) }
        ,

        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 4.dp
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .size(120.dp)
                .padding(12.dp)
                 //,elevation = 4.dp
                 ) {
               // Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie picture", modifier = Modifier.size(80.dp))
         //  AsyncImage(model = movie.images[0], contentDescription = movie.title )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                  //  placeholder = painterResource(R.drawable.placeholder),

                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Row() {
                Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) {
                    Text(
                        text = "${movie.title}",
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.body2

                    )

                    Text(
                        text = "Director: ${movie.director}",
                        style = MaterialTheme.typography.overline
                    )

                    Text(
                        text = "Released: ${movie.year}", style = MaterialTheme.typography.overline

                    )
                    if (!upDown) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "arrow is down",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(20.dp)
                                .clickable(onClick = { upDown = !upDown }))
                    }

                    AnimatedVisibility(visible = upDown) {
                        Column(modifier = Modifier.padding(4.dp)) {


                            Text(text = "Plot: ${movie.plot}",
                                style = MaterialTheme.typography.overline)
                            Divider(thickness = 1.dp, modifier = Modifier.padding())
                            Text(text = "Actors: ${movie.actors}",
                                style = MaterialTheme.typography.overline)
                            Text(text = "Genre: ${movie.genre}",
                                style = MaterialTheme.typography.overline)
                            Text(text = "Rating: ${movie.rating}",
                                style = MaterialTheme.typography.overline)
                            Icon(imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "arrow is up",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(20.dp)
                                    .clickable(onClick = { upDown = !upDown }))
                        }
                    }
                }

            }

            if(withOrWithoutHeart){
            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically).fillMaxWidth()) {
                Row(horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                   FavoriteIcon (movie = movie, favMovies = favMovies, yesHeart = {movie -> yesHeart(movie)}, noHeart = {movie ->noHeart(movie)})
                    //favorite(movie, yesHeart, noHeart)
                }}}
        }

    }
}

@Composable
fun HorizontalScrolllabelImageView(movie : Movie = getMovies()[0]){
   LazyRow {
       items(movie.images){ image->
           Card(
               modifier = Modifier
                   .padding(12.dp)
                   .size(240.dp),
               elevation = 4.dp
           ){
               AsyncImage(
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(image)
                       .crossfade(true)
                       .build(),

                   contentDescription = "movie picture",
                   )

           }


       }
   }
}

@Composable
fun FavoriteIcon (movie:Movie, favMovies: List<Movie>,
    yesHeart: (Movie) -> Unit = {},
                  noHeart: (Movie) -> Unit = {}

){
    var filledHeart by remember {

        mutableStateOf(checkHeart(favMovies, movie))
    }

    if(!filledHeart){
    Icon(imageVector = Icons.Default.FavoriteBorder,
        contentDescription = "heart outline",
        tint = Color.Blue,
        modifier = Modifier
            .padding(8.dp)
            .size(20.dp)
            .clickable(onClick = {
                    filledHeart = true;
                    yesHeart(movie)
            }

            ))

}
    else{
        Icon(imageVector = Icons.Default.Favorite,
            contentDescription = "heart filled",
            tint = Color.Blue,
            modifier = Modifier
                .padding(8.dp)
                .size(20.dp)
                .clickable(onClick = {
                        filledHeart = false;
                        noHeart(movie)

                }

                ))
    }}

fun checkHeart(favMovies: List<Movie>, movie: Movie): Boolean {
    when (movie) {
        in favMovies -> return true
        else -> return false
    }
}