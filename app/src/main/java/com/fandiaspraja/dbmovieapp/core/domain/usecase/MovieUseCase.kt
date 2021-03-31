package com.fandiaspraja.dbmovieapp.core.domain.usecase

import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getBanners(): Flow<Resource<List<Movie>>>

    fun getPopular(): Flow<Resource<List<Movie>>>

    fun getCoomingsoon(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun getAllTourism(): Flow<Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}