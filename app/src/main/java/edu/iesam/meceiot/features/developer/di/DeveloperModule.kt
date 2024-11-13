package edu.iesam.meceiot.features.developer.di

import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class DeveloperModule {

    @Single
    fun provideDeveloperApiService(retrofit: Retrofit) =
        retrofit.create(DeveloperApiService::class.java)
}