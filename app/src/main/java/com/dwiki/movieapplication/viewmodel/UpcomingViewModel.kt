package com.dwiki.movieapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.dwiki.movieapplication.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(private val repository: MainRepository):ViewModel() {
    fun upcomingMovie(token:String) = repository.upcomingMovie(token)
}