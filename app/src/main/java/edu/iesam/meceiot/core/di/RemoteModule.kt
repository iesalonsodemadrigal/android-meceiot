package edu.iesam.meceiot.core.di

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.core.data.remote.retrofit.AuthInterceptor
import edu.iesam.meceiot.features.login.data.local.LoginXmlDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_API = "https://meceiot.usal.es/g/"

@Module
@ComponentScan
class RemoteModule {

    @Single
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Single
    fun provideAuthInterceptor(loginXmlDataSource: LoginXmlDataSource): AuthInterceptor {
        return AuthInterceptor(loginXmlDataSource)
    }

    @Single
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
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

    @Single
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}