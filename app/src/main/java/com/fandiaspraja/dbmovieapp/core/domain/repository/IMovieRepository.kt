package com.fandiaspraja.dbmovieapp.core.domain.repository

import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getBanners(): Flow<Resource<List<Movie>>>

    fun getPopular(): Flow<Resource<List<Movie>>>

    fun getCoomingsoon(): Flow<Resource<List<Movie>>>

    fun getAllTourism(): Flow<Resource<List<Tourism>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}