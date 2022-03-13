package com.rohitjakhar.procreatortask.data.remote

import com.rohitjakhar.procreatortask.data.model.MovieDetailsModel
import com.rohitjakhar.procreatortask.data.model.MovieListModel
import com.rohitjakhar.procreatortask.data.model.toMovieDetails
import com.rohitjakhar.procreatortask.data.model.toMovieList
import com.rohitjakhar.procreatortask.data.webservice.AppService
import com.rohitjakhar.procreatortask.utils.Resource
import javax.inject.Inject

class Repository @Inject constructor(
    private val appService: AppService
) {
    suspend fun getMovieListRepo(): Resource<MovieListModel> {
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

    suspend fun getMovieDetailsRepo(): Resource<MovieDetailsModel> {
        val task = appService.getMovieDetails()
        if (task.isSuccessful) {
            task.body()?.let {
                return Resource.Success(data = it.toMovieDetails())
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
