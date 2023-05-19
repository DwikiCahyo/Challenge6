package com.dwiki.movieapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.dwiki.movieapplication.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingMovieViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {
    fun getTrendingMovie(token:String) = repository.trendingMovieWeek(token)
}