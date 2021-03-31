package com.fandiaspraja.dbmovieapp.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fandiaspraja.dbmovieapp.R
import com.fandiaspraja.dbmovieapp.core.domain.model.Movie
import com.fandiaspraja.dbmovieapp.databinding.ItemPopularBinding
import kotlin.collections.ArrayList

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ListViewHolder>(), Filterable {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    private var filmFilterList = ArrayList<Movie>()

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)

        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPopularBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + data.posterPath)
                    .into(imgPopular)

                titlePop.text = data.originalTitle
                popDesc.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Log.d("filters", charSearch)
                if (charSearch.isEmpty()) {
                    filmFilterList = listData
                } else {
                    val resultList = ArrayList<Movie>()
                    for (row in listData) {
                        if (row.originalTitle.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    filmFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filmFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filmFilterList = results?.values as ArrayList<Movie>
                Log.d("filterss", "$filmFilterList")
                listData = filmFilterList
                notifyDataSetChanged()
            }
        }
    }


}