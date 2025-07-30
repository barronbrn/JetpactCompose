package com.example.jetpactcompose.data.network

import com.example.jetpactcompose.data.dao.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse
}