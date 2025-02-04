package edu.iesam.meceiot.features.grafana.di

import edu.iesam.meceiot.features.grafana.data.remote.GrafanaService
import org.koin.core.annotation.Single
import retrofit2.Retrofit

class GrafanaModule {

    @Single
    fun provideGrafanaService(retrofit: Retrofit): GrafanaService =
        retrofit.create(GrafanaService::class.java)
}