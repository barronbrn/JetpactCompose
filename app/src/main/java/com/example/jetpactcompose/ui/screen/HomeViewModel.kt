package com.example.jetpactcompose.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpactcompose.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Ambil data now playing dan popular secara bersamaan (concurrent)
                val nowPlayingDeferred = async { movieRepository.getNowPlayingMovies() }
                val popularDeferred = async { movieRepository.getPopularMovies() }

                val nowPlayingMovies = nowPlayingDeferred.await()
                val popularMovies = popularDeferred.await()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        nowPlayingMovies = nowPlayingMovies,
                        popularMovies = popularMovies
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "An unexpected error occurred"
                    )
                }
            }
        }
    }
}