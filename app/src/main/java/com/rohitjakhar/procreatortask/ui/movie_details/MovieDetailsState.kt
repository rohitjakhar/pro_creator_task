package com.rohitjakhar.procreatortask.ui.movie_details

import com.rohitjakhar.procreatortask.data.model.MovieDetailsModel

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetailsModel? = null,
    val errorMessage: String = ""
)
