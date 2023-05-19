
package com.dwiki.movieapplication.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("unused", "unused", "unused")
@Entity(tableName = "favorite_movie")
data class Favorite(

    @PrimaryKey @ColumnInfo(name = "id") val id:Int,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "poster_path") val poster_path:String,
    @ColumnInfo(name = "release_data") val release_date:String

)
