package com.fandiaspraja.dbmovieapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fandiaspraja.dbmovieapp.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.ui.BannerAdapter
import com.fandiaspraja.dbmovieapp.core.ui.CoomingAdapter
import com.fandiaspraja.dbmovieapp.core.ui.MovieAdapter
import com.fandiaspraja.dbmovieapp.core.ui.PopularAdapter
import com.fandiaspraja.dbmovieapp.databinding.FragmentHomesBinding
import com.fandiaspraja.dbmovieapp.detailmovie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomesFragment : Fragment() {

    private val homesViewModel: HomesViewModel by viewModel()
    private var _binding: FragmentHomesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val bannerAdapter = BannerAdapter()
            bannerAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, selectedData)
                startActivity(intent)
            }

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, selectedData)
                startActivity(intent)
            }

            val coomingAdapter = CoomingAdapter()
            coomingAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, selectedData)
                startActivity(intent)
            }

            homesViewModel.bannerMovie.observe(viewLifecycleOwner, { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d("gimana", "${tourism.message}")
                            bannerAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            homesViewModel.popularMovie.observe(viewLifecycleOwner, { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d("gimana ya", "${tourism.message}")
                            movieAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            homesViewModel.coomingMovie.observe(viewLifecycleOwner, { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d("gimana aja", "${tourism.message}")
                            coomingAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvBanner) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = bannerAdapter
            }

            with(binding.rvPopular) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            with(binding.rvCooming) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = coomingAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}