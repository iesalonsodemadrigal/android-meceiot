package edu.iesam.meceiot.features.pantallasensor.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan()
class SensorModule {
    @Single
    fun provideSensorService(retrofit: Retrofit) =
        retrofit.create(SensorService::class.java)

    @Single
    fun provideSensorDao(db: MeceiotDataBase): SensorDao {
        return db.sensorDao()
    }
}