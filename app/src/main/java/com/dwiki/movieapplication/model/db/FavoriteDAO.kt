package com.dwiki.movieapplication.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDAO {

    @Insert
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_movie")
    fun getFavorite():LiveData<List<Favorite>>

    @Query("SELECT count(*) FROM favorite_movie WHERE favorite_movie.id = :id")
    suspend fun checkFavorite(id:Int):Int

    @Query("DELETE FROM favorite_movie WHERE favorite_movie.id = :id")
    suspend fun removeFavorite(id: Int): Int

}