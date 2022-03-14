package com.rohitjakhar.procreatortask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.procreatortask.data.remote.MovieRepository
import com.rohitjakhar.procreatortask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movieListState = MutableStateFlow<MovieListState>(MovieListState(true))
    val movieListState get() = _movieListState

    fun getMovieList() {
        viewModelScope.launch(IO) {
            when (val task = movieRepository.getMovieListRepo()) {
                is Resource.Empty -> {
                    _movieListState.value = MovieListState(error = task.message ?: "Movie List Empty")
                }
                is Resource.Error -> {
                    _movieListState.value = MovieListState(error = task.message ?: "Unknown Error")
                }
                is Resource.Loading -> {
                    _movieListState.value = MovieListState(true)
                }
                is Resource.Success -> {
                    _movieListState.value = MovieListState(movieList = task.data!!)
                }
            }
        }
    }
}
