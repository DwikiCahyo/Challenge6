package com.dwiki.movieapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiki.movieapplication.model.responsemodel.UpcomingResultsItem
import com.dwiki.movieapplication.databinding.ItemUpcomingMovieBinding

@Suppress("RemoveRedundantQualifierName", "RemoveRedundantQualifierName")
class UpcomingMovieAdapter(private val listUpcoming:List<UpcomingResultsItem>):RecyclerView.Adapter<UpcomingMovieAdapter.ViewHolder>() {

    private var onItemClickCallback: UpcomingMovieAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: UpcomingMovieAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(private val binding: ItemUpcomingMovieBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(listUpcoming: UpcomingResultsItem){
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500/${listUpcoming.posterPath}").into(binding.ivPosterFilmUpcoming)
            binding.tvTitleUpcoming.text = listUpcoming.title
            binding.tvReleaseDateUpcoming.text = "Release Date : ${listUpcoming.releaseDate}"

            binding.cvUpcomingFilm.setOnClickListener {
                onItemClickCallback?.onItemClicked(listUpcoming)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingMovieAdapter.ViewHolder {
        val binding = ItemUpcomingMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMovieAdapter.ViewHolder, position: Int) {
        holder.bind(listUpcoming[position])
    }

    override fun getItemCount(): Int  =  listUpcoming.size

    interface OnItemClickCallback {
        fun onItemClicked(listUpcoming: UpcomingResultsItem)
    }
}