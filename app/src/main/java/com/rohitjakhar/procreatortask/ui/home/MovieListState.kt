package com.rohitjakhar.procreatortask.ui.home

import com.rohitjakhar.procreatortask.data.model.MoviesModel

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<MoviesModel> = emptyList(),
    val error: String = ""
)
