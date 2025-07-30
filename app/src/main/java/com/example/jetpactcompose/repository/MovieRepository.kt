package com.example.jetpactcompose.repository

import com.example.jetpactcompose.data.dao.Movie
import com.example.jetpactcompose.data.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    // Ganti dengan API Key TMDb Anda
    private val apiKey = "YOUR_API_KEY"


    suspend fun getNowPlayingMovies(): List<Movie> {
        return try {
            val response = apiService.getNowPlayingMovies(apiKey)
            response.results.map { movieDto ->
                Movie(
                    id = movieDto.id,
                    title = movieDto.title,
                    overview = movieDto.overview,
                    posterUrl = "https://image.tmdb.org/t/p/w500${movieDto.posterPath}"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getPopularMovies(): List<Movie> {
        return try {
            val response = apiService.getPopularMovies(apiKey)
            response.results.map { movieDto ->
                Movie(
                    id = movieDto.id,
                    title = movieDto.title,
                    overview = movieDto.overview,
                    posterUrl = "https://image.tmdb.org/t/p/w500${movieDto.posterPath}"
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}