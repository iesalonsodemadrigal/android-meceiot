package edu.iesam.meceiot.features.externalresources.di

import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan()
class ExternalResourceModule {

    @Single
    fun provideExternalResourcesService(retrofit: Retrofit) =
        retrofit.create(ExternalResourcesService::class.java)

    @Single
    fun provideExternalResourcesDao(db: MeceiotDataBase): ExternalResourcesDao {
        return db.externalResourcesDao()
    }
}