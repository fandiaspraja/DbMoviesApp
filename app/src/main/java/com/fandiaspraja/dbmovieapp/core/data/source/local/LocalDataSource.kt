package com.fandiaspraja.dbmovieapp.core.data.source.local

import com.fandiaspraja.dbmovieapp.core.data.source.local.entity.MovieEntity
import com.fandiaspraja.dbmovieapp.core.data.source.local.entity.TourismEntity
import com.fandiaspraja.dbmovieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()


    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movie: List<MovieEntity>) = movieDao.insertMovie(movie)

    fun updateFavoriteMovie(movieEntity: MovieEntity) = movieDao.updateFavoriteMovie(movieEntity)

    fun deleteMovie(movieEntity: MovieEntity) = movieDao .deleteMovie(movieEntity)

    fun getAllTourism(): Flow<List<TourismEntity>> = movieDao.getAllTourism()

    fun getFavoriteTourism(): Flow<List<TourismEntity>> = movieDao.getFavoriteTourism()

    suspend fun insertTourism(tourismList: List<TourismEntity>) = movieDao.insertTourism(tourismList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

    fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
        tourism.isFavorite = newState
        movieDao.updateFavoriteTourism(tourism)
    }
}