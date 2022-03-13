package com.rohitjakhar.procreatortask.data.webservice

import com.rohitjakhar.procreatortask.BuildConfig
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.request()
        val authRequest = response.newBuilder()
            .addHeader("Authorization", BuildConfig.TOKEN)
            .build()
        return chain.proceed(authRequest)
    }
}