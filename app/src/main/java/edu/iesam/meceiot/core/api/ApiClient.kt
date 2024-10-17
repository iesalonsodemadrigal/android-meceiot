package edu.iesam.meceiot.core.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://https://meceiot.usal.es/api/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor("placeHolder", "placeHolder"))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)



}


