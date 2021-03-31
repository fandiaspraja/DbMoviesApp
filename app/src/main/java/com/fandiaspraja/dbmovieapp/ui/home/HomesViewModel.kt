package com.fandiaspraja.dbmovieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fandiaspraja.dbmovieapp.core.domain.usecase.MovieUseCase

class HomesViewModel(movieUseCase: MovieUseCase) : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    val bannerMovie = movieUseCase.getBanners().asLiveData()

    val popularMovie = movieUseCase.getPopular().asLiveData()

    val coomingMovie = movieUseCase.getCoomingsoon().asLiveData()

}