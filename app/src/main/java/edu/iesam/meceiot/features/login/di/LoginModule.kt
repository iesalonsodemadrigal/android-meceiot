package edu.iesam.meceiot.features.login.di

import edu.iesam.meceiot.features.login.data.remote.LoginApiService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class LoginModule {
    @Single
    fun provideLoginService(retrofit: Retrofit) =
        retrofit.create(LoginApiService::class.java)
}