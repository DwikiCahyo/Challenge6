package com.dwiki.movieapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiki.movieapplication.model.responsemodel.ResultsItem
import com.dwiki.movieapplication.databinding.ItemPosterFilmBinding

class TrendingMovieAdapter(private var listTrendingMovie:List<ResultsItem>):RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>() {

    private var onItemClickCallback:OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(private val binding:ItemPosterFilmBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(listTrendingMovie:ResultsItem){
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/${listTrendingMovie.posterPath}").into(binding.ivPosterFilm)

                binding.cvFilm.setOnClickListener {
                    onItemClickCallback?.onItemClicked(listTrendingMovie)
                }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingMovieAdapter.ViewHolder {
        val binding = ItemPosterFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendingMovieAdapter.ViewHolder, position: Int) {
        holder.bind(listTrendingMovie[position])
    }

    override fun getItemCount(): Int  = listTrendingMovie.size

    interface OnItemClickCallback {
        fun onItemClicked(listTrendingMovie: ResultsItem)
    }
}