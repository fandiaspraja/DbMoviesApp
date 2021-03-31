package com.fandiaspraja.dbmovieapp.core.domain.usecase

import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import com.fandiaspraja.dbmovieapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getBanners() = movieRepository.getBanners()

    override fun getPopular() = movieRepository.getPopular()

    override fun getCoomingsoon() = movieRepository.getCoomingsoon()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun getAllTourism() = movieRepository.getAllTourism()

    override fun getFavoriteTourism() = movieRepository.getFavoriteTourism()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = movieRepository.setFavoriteTourism(tourism, state)
}