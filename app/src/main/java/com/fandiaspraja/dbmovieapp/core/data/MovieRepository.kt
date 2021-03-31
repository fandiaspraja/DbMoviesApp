package com.fandiaspraja.dbmovieapp.core.data

import com.fandiaspraja.dbmovieapp.core.data.source.remote.network.ApiResponse
import com.fandiaspraja.dbmovieapp.core.data.source.local.LocalDataSource
import com.fandiaspraja.dbmovieapp.core.data.source.remote.RemoteDataSource
import com.fandiaspraja.dbmovieapp.core.data.source.remote.response.TourismResponse
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import com.fandiaspraja.dbmovieapp.core.domain.repository.IMovieRepository
import com.fandiaspraja.dbmovieapp.core.utils.AppExecutors
import com.fandiaspraja.dbmovieapp.core.utils.DataMapper
import com.fandiaspraja.moviedbapp.core.data.source.remote.response.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Tourism>> {
                return localDataSource.getAllTourism().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asFlow()

    override fun getBanners(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomainMovie(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true
//                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getBanners()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getPopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomainMovie(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true
//                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getPopular()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getCoomingsoon(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomainMovie(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true
//                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getCooming()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val movieList = DataMapper.mapResponsesToEntitiesMovie(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomainMovie(it) }
    }

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map { DataMapper.mapEntitiesToDomain(it) }}

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntityMovie(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

