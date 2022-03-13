package com.rohitjakhar.procreatortask.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.rohitjakhar.procreatortask.toImageLink

@Keep
data class MovieListDto(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    @Keep
    data class Dates(
        @SerializedName("maximum")
        val maximum: String,
        @SerializedName("minimum")
        val minimum: String
    )

    @Keep
    data class Result(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun MovieListDto.toMovieList(): List<MoviesModel> {
    val movieList = mutableListOf<MoviesModel>()
    this.results.forEach {
        movieList.add(
            MoviesModel(
                movieId = it.id.toString(),
                movieName = it.originalTitle,
                moviePoster = it.posterPath.toImageLink(),
                movieRating = it.voteAverage
            )
        )
    }
    return movieList
}
