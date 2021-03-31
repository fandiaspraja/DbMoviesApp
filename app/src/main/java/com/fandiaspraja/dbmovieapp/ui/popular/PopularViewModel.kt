package com.fandiaspraja.dbmovieapp.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase

class PopularViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    val popularMovie = movieUseCase.getPopular().asLiveData()
}