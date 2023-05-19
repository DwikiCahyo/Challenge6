package com.dwiki.movieapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.movieapplication.adapter.FavoriteAdapter
import com.dwiki.movieapplication.databinding.ActivityFavoriteBinding
import com.dwiki.movieapplication.model.db.Favorite
import com.dwiki.movieapplication.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFavoriteBinding
    private val favoriteViewModel:FavoriteViewModel  by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite Movie"

        favoriteViewModel.getFavoriteMovie().observe(this){
            binding.rvFavorite.apply {
                layoutManager = LinearLayoutManager(
                    this@FavoriteActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                favoriteAdapter= FavoriteAdapter(it)
                adapter = favoriteAdapter
            }


        }


    }
}