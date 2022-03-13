package com.rohitjakhar.procreatortask.data.model.dto
import androidx.annotation.Keep

import com.google.gson.annotations.SerializedName
import com.rohitjakhar.procreatortask.data.model.MovieDetailsModel
import com.rohitjakhar.procreatortask.toImageLink

@Keep
data class MovieDetailsDto(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String,
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
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    @Keep
    data class BelongsToCollection(
        @SerializedName("backdrop_path")
        val backdropPath: Any?,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("poster_path")
        val posterPath: Any?
    )

    @Keep
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    @Keep
    data class ProductionCompany(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: Any?,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String
    )

    @Keep
    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("name")
        val name: String
    )

    @Keep
    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("name")
        val name: String
    )
}

fun MovieDetailsDto.toGenreString(): String {
    val genreString: String = ""
    genres.forEach {
        genreString.plus("${it.name}, ")
    }
    return genreString.removeSuffix(" ,")
}
fun MovieDetailsDto.toMovieDetails(): MovieDetailsModel {
    return MovieDetailsModel(
        movieName = originalTitle,
        movieImage = posterPath.toImageLink(),
        movieRating = voteAverage,
        movieDescription = overview,
        movieGenre = toGenreString(),
        movieDuration = runtime,
        movieTagLine = tagline.ifEmpty { null },
        moviePH = productionCompanies.first().name
    )
}