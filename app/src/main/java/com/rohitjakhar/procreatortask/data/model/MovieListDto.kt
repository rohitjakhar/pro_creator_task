package com.rohitjakhar.procreatortask.data.model

data class MovieListDto(
    val id: String
)

fun MovieListDto.toMovieList(): MovieListModel {
    return MovieListModel()
}
