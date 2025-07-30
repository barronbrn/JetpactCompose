package com.example.jetpactcompose.ui.screen

import com.example.jetpactcompose.model.Movie

data class HomeScreenState(
    val isLoading: Boolean = false,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val error: String? = null
)
