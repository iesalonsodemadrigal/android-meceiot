package edu.iesam.meceiot.features.grafana.di

import edu.iesam.meceiot.features.grafana.data.remote.GrafanaService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class GrafanaModule {

    @Single
    fun provideGrafanaService(retrofit: Retrofit): GrafanaService {
        return retrofit.create(GrafanaService::class.java)
    }
}