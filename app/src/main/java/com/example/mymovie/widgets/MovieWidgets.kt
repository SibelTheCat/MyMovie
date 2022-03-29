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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.mymovie.R
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             //CallBack Function kann als lambda übergeben werden
             onItemClick: (String)-> Unit = {}){
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
            Column(modifier = Modifier.align(alignment = Alignment.CenterVertically)) { Text(
                text = "${movie.title}", fontSize = 20.sp, style = MaterialTheme.typography.body2

            )
                Text(
                    text = "Director: ${movie.director}", style = MaterialTheme.typography.overline

                )
                Text(
                    text = "Released: ${movie.year}",style = MaterialTheme.typography.overline

                )
                if (!upDown){
                    Icon(imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "arrow is down",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(20.dp)
                            .clickable(onClick = { upDown = !upDown }))
                }

                AnimatedVisibility(visible = upDown) {
                    Column(modifier = Modifier.padding(4.dp)) {


                        Text(text = "Plot: ${movie.plot}",style = MaterialTheme.typography.overline)
                        Divider(thickness = 1.dp, modifier = Modifier.padding())
                        Text(text = "Actors: ${movie.actors}",style = MaterialTheme.typography.overline)
                        Text(text = "Genre: ${movie.genre}",style = MaterialTheme.typography.overline)
                        Text(text = "Rating: ${movie.rating}",style = MaterialTheme.typography.overline)
                        Icon(imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "arrow is up",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(20.dp)
                                .clickable(onClick = { upDown = !upDown }))
                    }
                }}
        }

    }
}

@Composable
fun HorizontalScrolllabelImageView(movie : Movie = getMovies()[0]){
   LazyRow {
       items(movie.images){ image->
           Card(
               modifier = Modifier.padding(12.dp).size(240.dp),
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