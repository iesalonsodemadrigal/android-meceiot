package edu.iesam.meceiot.core.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://miceiot.iesalonsodemadrigal.es/api/v1/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor("placeHolder", "placeHolder"))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)



}


