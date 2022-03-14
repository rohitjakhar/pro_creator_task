package com.rohitjakhar.procreatortask.data.remote

import android.util.Log
import com.rohitjakhar.procreatortask.data.model.MovieDetailsModel
import com.rohitjakhar.procreatortask.data.model.MoviesModel
import com.rohitjakhar.procreatortask.data.model.dto.getDirector
import com.rohitjakhar.procreatortask.data.model.dto.getWriter
import com.rohitjakhar.procreatortask.data.model.dto.toMovieDetails
import com.rohitjakhar.procreatortask.data.model.dto.toMovieList
import com.rohitjakhar.procreatortask.data.webservice.AppService
import com.rohitjakhar.procreatortask.utils.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val appService: AppService
) {
    suspend fun getMovieListRepo(): Resource<List<MoviesModel>> {
        val task = appService.getMovieList()
        if (task.isSuccessful) {
            task.body()?.let {
                return Resource.Success(data = it.toMovieList())
            } ?: run { return Resource.Empty() }
        } else {
            return when (task.code()) {
                402 -> {
                    Resource.Error(message = "")
                }
                503 -> {
                    Resource.Error(message = "")
                }
                else -> {
                    Resource.Error(message = task.message())
                }
            }
        }
    }

    suspend fun getMovieDetailsRepo(movieId: String): Resource<MovieDetailsModel> {
        val task = appService.getMovieDetails(movieId)
        if (task.isSuccessful) {
            task.body()?.let {
                val creditTask = appService.getMovieCredit(movieId)
                return if (creditTask.isSuccessful) {
                    if (creditTask.body() != null) {
                        Resource.Success(
                            data = it.toMovieDetails().copy(
                                movieDirector = creditTask.body()!!.getDirector(),
                                movieWriter = creditTask.body()!!.getWriter()
                            )
                        )
                    } else {
                        Resource.Success(data = it.toMovieDetails())
                    }
                } else {
                    Resource.Success(data = it.toMovieDetails())
                }
            } ?: run {
                return Resource.Empty()
            }
        } else {
            return when (task.code()) {
                402 -> {
                    Resource.Error(message = "")
                }
                503 -> {
                    Resource.Error(message = "")
                }
                else -> {
                    Resource.Error(message = task.message())
                }
            }
        }
    }
}
