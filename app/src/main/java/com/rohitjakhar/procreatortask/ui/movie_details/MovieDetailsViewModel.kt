package com.rohitjakhar.procreatortask.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.procreatortask.data.remote.MovieRepository
import com.rohitjakhar.procreatortask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private var _movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetailState get() = _movieDetailsState

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch(IO) {
            val task = movieRepository.getMovieDetailsRepo(movieId)
            when (task) {
                is Resource.Empty -> {
                    _movieDetailsState.value = MovieDetailsState(false, null)
                }
                is Resource.Error -> {
                    _movieDetailsState.value = MovieDetailsState(
                        false,
                        null,
                        errorMessage = task.message ?: "Unknown Error"
                    )
                }
                is Resource.Loading -> {
                    _movieDetailsState.value = MovieDetailsState(true)
                }
                is Resource.Success -> {
                    _movieDetailsState.value = MovieDetailsState(false, movieDetails = task.data)
                }
            }
        }
    }
}
