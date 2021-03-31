package com.fandiaspraja.dbmovieapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fandiaspraja.dbmovieapp.R
import com.fandiaspraja.dbmovieapp.core.ui.FavoriteAdapter
import com.fandiaspraja.dbmovieapp.databinding.FragmentFavoriteMovieBinding
import com.fandiaspraja.dbmovieapp.detailmovie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModel()
    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoriteAdapter = FavoriteAdapter()
            favoriteAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, selectedData)
                startActivity(intent)
            }


            favoriteMovieViewModel.favoriteMovie.observe(viewLifecycleOwner, { movieFav ->
                favoriteAdapter.setData(movieFav)
                binding.viewEmpty.root.visibility = if (movieFav.isNotEmpty()) View.GONE else View.VISIBLE
            })

            binding.searchFav.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    Log.d("filter", "$newText")
                    favoriteAdapter.filter.filter(newText)
                    return true
                }

            })

            with(binding.rvPop) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}