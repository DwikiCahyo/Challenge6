@file:Suppress("unused", "unused", "unused")

package com.dwiki.movieapplication.model.responsemodel

import com.google.gson.annotations.SerializedName

data class ResponseUpcomingMovie(

	@field:SerializedName("dates")
	val dates: Dates,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<UpcomingResultsItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class UpcomingResultsItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("popularity")
	val popularity: Any,

	@field:SerializedName("vote_average")
	val voteAverage: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("vote_count")
	val voteCount: Int
)

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
	"unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
	"unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
	"unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
	"unused", "unused", "unused"
)
data class Dates(

	@field:SerializedName("maximum")
	val maximum: String,

	@field:SerializedName("minimum")
	val minimum: String
)
