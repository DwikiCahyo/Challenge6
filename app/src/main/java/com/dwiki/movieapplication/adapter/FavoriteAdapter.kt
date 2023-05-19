package com.dwiki.movieapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiki.movieapplication.databinding.ItemUpcomingMovieBinding
import com.dwiki.movieapplication.model.db.Favorite

class FavoriteAdapter (private val listFavorite:List<Favorite>):RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(private val binding:ItemUpcomingMovieBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite){
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/${favorite.poster_path}").into(binding.ivPosterFilmUpcoming)
            binding.tvTitleUpcoming.text = favorite.title
            binding.tvReleaseDateUpcoming.text = "Release Date : ${favorite.release_date}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUpcomingMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFavorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listFavorite[position])
    }
}