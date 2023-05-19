package com.dwiki.movieapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.movieapplication.R
import com.dwiki.movieapplication.adapter.TrendingMovieAdapter
import com.dwiki.movieapplication.adapter.UpcomingMovieAdapter
import com.dwiki.movieapplication.model.responsemodel.ResultsItem
import com.dwiki.movieapplication.model.responsemodel.UpcomingResultsItem
import com.dwiki.movieapplication.databinding.ActivityHomeBinding
import com.dwiki.movieapplication.util.Status
import com.dwiki.movieapplication.viewmodel.LoginViewModel
import com.dwiki.movieapplication.viewmodel.TrendingMovieViewModel
import com.dwiki.movieapplication.viewmodel.UpcomingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val trendingViewModel:TrendingMovieViewModel by viewModels()
    private val upcomingViewModel:UpcomingViewModel by viewModels()
    private val loginViewModel:LoginViewModel by viewModels()
    private lateinit var trendingMovieAdapter: TrendingMovieAdapter
    private lateinit var upcomingMovieAdapter:UpcomingMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set app bar
        binding.toolbar.title = ""
        setSupportActionBar(binding.toolbar)
        setupTrendingMovie()
        setupUpcomingMovie()


        val username = loginViewModel.getUsernamePreferences("key_username")
//        binding.tvWelcome.text = getString(R.string.welcome,username)
        binding.tvWelcome.text = "Welcome, $username"
    }

    private fun setupUpcomingMovie() {
        upcomingViewModel.upcomingMovie("3dc33f88c0689c50b65f6a89a2b66889").observe(this){ upcomingMovie ->
            when(upcomingMovie.status){
                Status.LOADING -> {
                    binding.rvUpcomingMovie.visibility = View.INVISIBLE
                    binding.shimmerFrameLayoutUpcoming.startShimmer()
                    Log.d("HomeActivity Upcoming", "Loading Upcoming")
                }
                Status.SUCCESS -> {
                    upcomingMovie.data?.results?.map {Log.d("HomeActivity Upcoming", it.title)}
                    binding.rvUpcomingMovie.visibility = View.VISIBLE
                    binding.shimmerFrameLayoutUpcoming.stopShimmer()
                    binding.shimmerFrameLayoutUpcoming.visibility = View.GONE
                    if (upcomingMovie != null){
                        val listUpcomingMovie = upcomingMovie.data?.results
                        binding.rvUpcomingMovie.apply {
                            layoutManager = LinearLayoutManager(
                                this@HomeActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            upcomingMovieAdapter = UpcomingMovieAdapter(listUpcomingMovie!!)
                            adapter = upcomingMovieAdapter
                        }

                        upcomingMovieAdapter.setOnItemClickCallback(object :UpcomingMovieAdapter.OnItemClickCallback{
                            override fun onItemClicked(listUpcoming: UpcomingResultsItem) {
                                DetailDialogFragment(listUpcoming.originalTitle,listUpcoming.releaseDate,listUpcoming.overview,listUpcoming.posterPath,listUpcoming.id).show(supportFragmentManager,"Detail Fragment")
                            }
                        })
                    }
                }
                Status.ERROR -> {
                    binding.rvUpcomingMovie.visibility = View.VISIBLE
                    binding.shimmerFrameLayoutUpcoming.stopShimmer()
                    Log.d("HomeActivity Upcoming", "Error : ${upcomingMovie.message}")

                }

            }

        }
    }

    private fun setupTrendingMovie() {
        trendingViewModel.getTrendingMovie("3dc33f88c0689c50b65f6a89a2b66889")
            .observe(this) { trendingMovie ->
                when (trendingMovie.status) {
                    Status.LOADING -> {
                        Log.d("HomeActivity", "Loading")
                        binding.rvTrendingMovie.visibility = View.INVISIBLE
                        binding.shimmerFrameLayout.startShimmer()
                    }
                    Status.SUCCESS -> {
                        trendingMovie.data?.results?.map { Log.d("HomeActivity", it.title) }
                        binding.rvTrendingMovie.visibility = View.VISIBLE
                        binding.shimmerFrameLayout.stopShimmer()
                        binding.shimmerFrameLayout.visibility = View.GONE

                        if (trendingMovie != null) {
                            val listTrendingMovie = trendingMovie.data?.results
                            binding.rvTrendingMovie.apply {
                                layoutManager = LinearLayoutManager(
                                    this@HomeActivity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                trendingMovieAdapter = TrendingMovieAdapter(listTrendingMovie!!)
                                adapter = trendingMovieAdapter
                            }
                            trendingMovieAdapter.setOnItemClickCallback(object :
                                TrendingMovieAdapter.OnItemClickCallback {
                                override fun onItemClicked(listTrendingMovie: ResultsItem) {
                                    DetailDialogFragment(listTrendingMovie.originalTitle,listTrendingMovie.releaseDate,listTrendingMovie.overview,listTrendingMovie.posterPath,listTrendingMovie.id).show(supportFragmentManager,"Detail Fragment")
                                }
                            })
                        }
                    }
                    Status.ERROR -> {
                        binding.rvTrendingMovie.visibility = View.VISIBLE
                        binding.shimmerFrameLayout.stopShimmer()
                        Log.d("HomeActivity", "Error : ${trendingMovie.message}")
                    }
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.profile){
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "No Profile Icon", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}