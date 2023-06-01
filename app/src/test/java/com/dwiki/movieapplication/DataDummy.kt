package com.dwiki.movieapplication

import com.dwiki.movieapplication.model.db.Favorite
import com.dwiki.movieapplication.model.responsemodel.*

object DataDummy {

    fun dummyUpcomingResponse(): ResponseUpcomingMovie{
        return ResponseUpcomingMovie(
            dates = Dates(
                maximum = "2022-03-22",
                minimum = "2022-03-19"
            ),
            page = 1,
            totalPages = 1,
            results = dummyUpcomingResultItem(),
            totalResults = 1
        )
    }

    fun dummyTrendingResponse():ResponseTrendingMovieWeek{
        return ResponseTrendingMovieWeek(
            page = 1,
            totalPages = 1,
            results = dummyTrendingResultItem(),
            totalResults = 1
        )

    }

    fun generateDummyFavourite():List<Favorite>{
        val favoriteList = ArrayList<Favorite>()
        for (i in 0..5){
            val favorite = Favorite(
                id = i,
                title = "title $i",
                poster_path = "/$i.jpg",
                release_date = "2022-03-0$i",
                overview = "overview $i"
            )
            favoriteList.add(favorite)
        }
        return favoriteList
    }

    private fun dummyTrendingResultItem():List<ResultsItem>{
        val listTrendingResultsItem = ArrayList<ResultsItem>()

        for (i in 0..5){
            val trendingResultItem = ResultsItem(
                overview = "overview $i",
                originalLanguage = "en $i",
                originalTitle = "title $i",
                video = false,
                title = "title $i",
                genreIds = listOf(1,2,3),
                posterPath = "/$i.jpg",
                backdropPath = "/$i.jpg",
                mediaType = "media type $i",
                releaseDate = "2022-03-22",
                popularity = 0,
                voteAverage = 0.0,
                id = i,
                adult = false,
                voteCount = 0
            )
            listTrendingResultsItem.add(trendingResultItem)
        }
        return listTrendingResultsItem
    }


    private fun dummyUpcomingResultItem(): List<UpcomingResultsItem>{
        val listUpcomingResultItem = ArrayList<UpcomingResultsItem>()

        for (i in 0..5){
            val upcomingResultItem = UpcomingResultsItem(
                posterPath = "/$i.jpg",
                overview = "overview $i",
                originalLanguage = "en $i",
                originalTitle = "title $i",
                video = false,
                title = "title $i",
                genreIds = listOf(1,2,3),
                backdropPath = "/$i.jpg",
                releaseDate = "2022-03-22",
                popularity = 0,
                voteAverage = 0.0,
                id = i,
                adult = false,
                voteCount = 0,
            )
            listUpcomingResultItem.add(upcomingResultItem)
        }
        return listUpcomingResultItem
    }
}