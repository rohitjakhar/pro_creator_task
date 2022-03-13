package com.rohitjakhar.procreatortask.movie_list

import com.nhaarman.mockitokotlin2.mock
import com.rohitjakhar.procreatortask.data.webservice.AppService
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListRepositoryShould {
    private val service: AppService = mock()
}
