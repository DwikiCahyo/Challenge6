package com.dwiki.movieapplication.network.api

import com.dwiki.movieapplication.model.responsemodel.ResponseTrendingMovieWeek
import com.dwiki.movieapplication.model.responsemodel.ResponseUpcomingMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovieWeek(
        @Query("api_key") api_key:String
    ): Response<ResponseTrendingMovieWeek>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("api_key") api_key: String
    ):Response<ResponseUpcomingMovie>

}