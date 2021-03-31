package com.fandiaspraja.dbmovieapp.core.data.source.remote.network

import com.fandiaspraja.dbmovieapp.core.data.source.remote.response.ListTourismResponse
import com.fandiaspraja.moviedbapp.core.data.source.remote.response.MovieDetailResponse
import com.fandiaspraja.moviedbapp.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("list")
    suspend fun getList(): ListTourismResponse

    @GET("discover/movie")
    suspend fun getBanners(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("sort_by") sortBy: String,
                           @Query("include_adult") incAdult: Boolean, @Query("include_video") incVideo: Boolean, @Query("page") page: Int): MovieResponse

    @GET("discover/movie")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("sort_by") sortBy: String,
                                @Query("include_adult") incAdult: Boolean, @Query("include_video") incVideo: Boolean, @Query("page") page: Int): MovieResponse

    @GET("discover/movie")
    suspend fun getComingsoon(@Query("api_key") apiKey: String, @Query("language") language: String, @Query("sort_by") sortBy: String,
                              @Query("include_adult") incAdult: Boolean, @Query("include_video") incVideo: Boolean, @Query("page") page: Int,
                              @Query("year") year: String): MovieResponse

    @GET("discover/movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movie_id: String, @Query("api_key") apiKey: String, @Query("language") language: String): MovieDetailResponse



}