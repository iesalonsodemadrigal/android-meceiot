package edu.iesam.meceiot.features.sensorpanels.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.SensorDao
import edu.iesam.meceiot.features.sensorpanels.data.remote.SensorPanelsService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class SensorPanelsModule {
    @Single
    fun provideSensorPanelsService(retrofit: Retrofit) =
        retrofit.create(SensorPanelsService::class.java)

    @Single
    fun providePanelDao(db: MeceiotDataBase): PanelDao {
        return db.panelDao()
    }

    @Single
    fun provideSensorDao(db: MeceiotDataBase): SensorDao {
        return db.sensorDao()
    }

}