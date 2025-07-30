package com.example.jetpactcompose.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpactcompose.model.Movie
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    // Mengambil state dari ViewModel
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Tampilkan daftar film jika tidak loading dan tidak ada error
        if (!state.isLoading && state.error == null) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    MovieSection(
                        title = "Now Playing",
                        movies = state.nowPlayingMovies,
                        onMovieClick = { movieId ->
                            Toast.makeText(context, "Movie ID: $movieId", Toast.LENGTH_SHORT).show()
                            // TODO: Navigasi ke Detail Screen
                        }
                    )
                }
                item {
                    MovieSection(
                        title = "Popular",
                        movies = state.popularMovies,
                        onMovieClick = { movieId ->
                            Toast.makeText(context, "Movie ID: $movieId", Toast.LENGTH_SHORT).show()
                            // TODO: Navigasi ke Detail Screen
                        }
                    )
                }
            }
        }

        // Tampilkan loading indicator di tengah layar
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        // Tampilkan pesan error jika ada
        state.error?.let { error ->
            Text(
                text = error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun MovieSection(
    title: String,
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, onMovieClick = onMovieClick)
            }
        }
    }
}