package edu.iesam.meceiot.core.api

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val username: String, private val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", Credentials.basic(username, password))
            .build()
        return chain.proceed(authenticatedRequest)
    }
}