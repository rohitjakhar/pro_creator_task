package com.rohitjakhar.procreatortask.data.webservice

import com.rohitjakhar.procreatortask.data.model.MovieDetailsDto
import com.rohitjakhar.procreatortask.data.model.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface AppService {

    @GET("")
    suspend fun getMovieList(): Response<MovieListDto>

    @POST("")
    suspend fun getMovieDetails(): Response<MovieDetailsDto>
}
