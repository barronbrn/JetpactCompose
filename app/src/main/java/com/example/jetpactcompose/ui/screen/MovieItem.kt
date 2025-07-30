package com.example.jetpactcompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetpactcompose.model.Movie

@Composable
fun MovieItem(
    movie: Movie,
    onMovieClick: (Int) -> Unit // Callback untuk navigasi nanti
) {
    AsyncImage(
        model = movie.posterUrl,
        contentDescription = movie.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(150.dp) // Lebar poster
            .clip(RoundedCornerShape(16.dp)) // Membuat sudut melengkung
            .clickable { onMovieClick(movie.id) }
    )
}