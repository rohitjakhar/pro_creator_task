package com.rohitjakhar.procreatortask.data.model

data class MovieDetailsDto(
    val movieName: String
)

fun MovieDetailsDto.toMovieDetails(): MovieDetailsModel {
    return MovieDetailsModel()
}