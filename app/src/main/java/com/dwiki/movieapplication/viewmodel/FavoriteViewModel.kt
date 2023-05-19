package com.dwiki.movieapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwiki.movieapplication.model.db.Favorite
import com.dwiki.movieapplication.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor( private val mainRepository: MainRepository):ViewModel() {

    fun insertMovie(title:String,id:Int,posterPath:String,releaseDate:String){
        viewModelScope.launch {
            val movie = Favorite(id,title,posterPath,releaseDate)
            mainRepository.insertFavoriteMovie(movie)
        }
    }

    fun removeMovie(id:Int){
        viewModelScope.launch {
            mainRepository.deleteFavoriteMovie(id)
        }
    }

    suspend fun checkUserFavorite(id: Int) = mainRepository.isFavoriteMovie(id)

    fun getFavoriteMovie(): LiveData<List<Favorite>>{
        return mainRepository.getAllFavoriteMovie()
    }
}