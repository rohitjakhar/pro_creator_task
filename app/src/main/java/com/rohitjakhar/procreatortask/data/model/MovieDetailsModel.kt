package com.rohitjakhar.procreatortask.data.model

data class MovieDetailsModel(
    val movieName: String,
    val movieTagLine: String?,
    val movieImage: String,
    val movieRating: Double,
    val movieDuration: Int,
    val movieDirector: String? = null,
    val movieWriter: String? = null,
    val movieGenre: String,
    val moviePH: String,
    val movieDescription: String,
)
