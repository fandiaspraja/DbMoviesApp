package com.fandiaspraja.dbmovieapp.detailmovie

import androidx.lifecycle.ViewModel
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) = movieUseCase.setFavoriteMovie(movie, newStatus)

}