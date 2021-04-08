package com.fandiaspraja.dbmovieapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}