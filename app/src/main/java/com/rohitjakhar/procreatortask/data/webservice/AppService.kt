package com.rohitjakhar.procreatortask.data.webservice

import com.rohitjakhar.procreatortask.data.model.MovieCreditDto
import com.rohitjakhar.procreatortask.data.model.MovieDetailsDto
import com.rohitjakhar.procreatortask.data.model.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {

    @GET("movie/now_playing")
    suspend fun getMovieList(): Response<MovieListDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredit(@Path("movie_id") movieId: String): Response<MovieCreditDto>
}
