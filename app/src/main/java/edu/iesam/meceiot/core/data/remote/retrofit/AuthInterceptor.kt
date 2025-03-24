package edu.iesam.meceiot.core.data.remote.retrofit

import edu.iesam.meceiot.features.login.data.local.LoginXmlDataSource
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val loginXmlDataSource: LoginXmlDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestUrl = originalRequest.url.toString()

        // Skip authentication for login endpoints or when credentials aren't available
        if (requestUrl.contains("/login") ||
            requestUrl.contains("login.php")
        ) {
            return chain.proceed(originalRequest)
        }

        // Only authenticate requests that need it
        val authenticatedRequest = if (requestUrl.contains("/api/") ||
            requestUrl.contains("/g/") ||
            requestUrl.contains("grafana")
        ) {
            originalRequest.newBuilder()
                .header(
                    "Authorization", Credentials.basic(
                        loginXmlDataSource.getCredentials()?.user ?: "",
                        loginXmlDataSource.getCredentials()?.password ?: ""
                    )
                )
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(authenticatedRequest)
    }
}

/*class AuthInterceptor(private val loginXmlDataSource: LoginXmlDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Only add auth for Grafana API requests
        if (!originalRequest.url.toString().contains("meceiot.usal.es/g/")) {
            return chain.proceed(originalRequest)
        }

        val credentials = loginXmlDataSource.getCredentials() ?: return chain.proceed(originalRequest)

        val auth = "${credentials.user}:${credentials.password}"
        val encodedAuth = Base64.encodeToString(auth.toByteArray(), Base64.NO_WRAP)

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Basic $encodedAuth")
            .build()

        return chain.proceed(newRequest)
    }
}*/