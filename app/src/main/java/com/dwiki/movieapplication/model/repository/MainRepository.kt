package com.dwiki.movieapplication.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dwiki.movieapplication.model.db.Favorite
import com.dwiki.movieapplication.model.db.FavoriteDAO
import com.dwiki.movieapplication.network.api.ApiService
import com.dwiki.movieapplication.model.responsemodel.ResponseTrendingMovieWeek
import com.dwiki.movieapplication.model.responsemodel.ResponseUpcomingMovie
import com.dwiki.movieapplication.util.Resources
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val favoriteDAO: FavoriteDAO
) {

    fun trendingMovieWeek(token:String):LiveData<Resources<ResponseTrendingMovieWeek>> = liveData {
        emit(Resources.loading(null))
        delay(1000)
        try {
            val response = apiService.getTrendingMovieWeek(token)
            if (response.isSuccessful){
                emit(Resources.success(response.body()))
                Log.d(TAG,"succes trending movie week: ${response.message()}")
            } else {
                emit(Resources.error(response.errorBody()?.string(),null))
                Log.e(TAG,"error trending movie week : ${response.errorBody()?.string()}")
            }

        } catch (e:Exception){
            emit(Resources.error(e.message.toString(),null))
            Log.e(TAG,"error connect api : ${e.cause.toString()}")
        }
    }

    fun upcomingMovie(token: String):LiveData<Resources<ResponseUpcomingMovie>> = liveData {
        emit(Resources.loading(null))
        try {
            val response = apiService.getUpcomingMovie(token)
            if (response.isSuccessful){
                emit(Resources.success(response.body()))
                Log.d(TAG,"succes upcoming movie week: ${response.message()}")
            } else {
                emit(Resources.error(response.errorBody()?.string(),null))
                Log.e(TAG,"error upcoming movie week : ${response.errorBody()?.string()}")
                throw RuntimeException("error connect api  : ${response.errorBody()?.string()}")
            }
        } catch (e:Exception){
            emit(Resources.error(e.message.toString(),null))
            Log.e(TAG,"error connect api : ${e.cause.toString()}")
            //crashlyics
            throw RuntimeException("error connect api")

        }
    }

    //insert favorite movie
    suspend fun insertFavoriteMovie( favorite: Favorite) = favoriteDAO.addFavorite(favorite)

    //delete favorite movie
    suspend fun deleteFavoriteMovie(idMovie:Int) = favoriteDAO.removeFavorite(idMovie)

    //get all favorite movie
    fun getAllFavoriteMovie() = favoriteDAO.getFavorite()

    //check is movie favorite
    suspend fun isFavoriteMovie(idMovie:Int):Int = favoriteDAO.checkFavorite(idMovie)




    companion object{
        private const val TAG = "MainRepository"
    }
}