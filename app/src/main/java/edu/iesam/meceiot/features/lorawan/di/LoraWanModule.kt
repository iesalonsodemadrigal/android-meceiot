package edu.iesam.meceiot.features.lorawan.di

import edu.iesam.meceiot.features.lorawan.data.remote.LoraWanApiService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


@Module
@ComponentScan
class LoraWanModule {

    @Single
    fun provideInfoLorawanService(retrofit: Retrofit) =
        retrofit.create(LoraWanApiService::class.java)
}
