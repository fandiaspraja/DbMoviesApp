package com.fandiaspraja.dbmovieapp.ui.popular

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.fandiaspraja.dbmovieapp.R
import com.fandiaspraja.dbmovieapp.core.data.Resource
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.core.ui.PopularAdapter
import com.fandiaspraja.dbmovieapp.databinding.FragmentPopularBinding
import com.fandiaspraja.dbmovieapp.detailmovie.DetailMovieActivity
import org.koin.android.viewmodel.ext.android.viewModel

class PopularFragment : Fragment() {

    private val popularViewModel: PopularViewModel by viewModel()
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val popularAdapter = PopularAdapter()
            popularAdapter.onItemClick = {selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA_MOVIE, selectedData)
                startActivity(intent)
            }

            popularViewModel.popularMovie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.d("gimana", "${movie.message}")
                            popularAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = movie.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            binding.searchPop.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    Log.d("filter", "$newText")
                    popularAdapter.filter.filter(newText)
                    return true
                }

            })

            with(binding.rvPop) {
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = popularAdapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}