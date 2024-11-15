package edu.iesam.meceiot.core.api.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_API = "https://meceiot.usal.es/"

class ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_API)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor("placeHolder", "placeHolder"))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)


}