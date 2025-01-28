package edu.iesam.meceiot.core.di

import edu.iesam.meceiot.core.api.retrofit.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_API = "https://meceiot.usal.es/"

@Module
@ComponentScan
class RemoteModule {

    private val url = "https://sandbox.aulapragmatica.es/"

    @Single
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Single
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor("placeHolder", "placeHolder"))
            .build()
        return okHttpClient
    }

    @Single
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}