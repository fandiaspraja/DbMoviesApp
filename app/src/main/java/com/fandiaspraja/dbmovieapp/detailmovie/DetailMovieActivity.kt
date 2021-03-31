package com.fandiaspraja.dbmovieapp.detailmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fandiaspraja.dbmovieapp.R
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.domain.model.Tourism
import com.fandiaspraja.dbmovieapp.databinding.ActivityDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
    }

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA_MOVIE)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            supportActionBar?.title = movie.title
            binding.content.tvDesc.text = movie.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                .into(binding.ivDetailImage)

            binding.duration.text = movie.releaseDate

            binding.titleMov.text = movie.originalTitle
            binding.imgBack.setOnClickListener {
                onBackPressed()
            }

            var statusFavorite = movie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFav.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.btn_fav_check))
        } else {
            binding.btnFav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.btn_fav))
        }
    }
}