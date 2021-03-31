package com.fandiaspraja.dbmovieapp.core.di

import android.content.Context

import com.fandiaspraja.dbmovieapp.core.data.source.local.LocalDataSource
import com.fandiaspraja.dbmovieapp.core.data.source.local.room.MovieDatabase

import com.fandiaspraja.dbmovieapp.core.data.MovieRepository
import com.fandiaspraja.dbmovieapp.core.data.source.remote.RemoteDataSource
import com.fandiaspraja.dbmovieapp.core.data.source.remote.network.ApiConfig
import com.fandiaspraja.dbmovieapp.core.domain.repository.IMovieRepository
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieInteractor
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase
import com.fandiaspraja.dbmovieapp.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}
