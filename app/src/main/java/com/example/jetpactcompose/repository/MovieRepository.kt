package com.example.jetpactcompose.repository

import com.example.jetpactcompose.model.Movie
import com.example.jetpactcompose.data.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    private val apiKey = "be5fa6be346e0785507d5e09a76973fb"

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