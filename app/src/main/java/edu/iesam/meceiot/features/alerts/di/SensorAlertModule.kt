package edu.iesam.meceiot.features.alerts.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.alerts.data.local.db.PanelDao
import edu.iesam.meceiot.features.alerts.data.local.db.SensorDao
import edu.iesam.meceiot.features.alerts.data.remote.SensorService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


@Module
@ComponentScan
class SensorAlertModule {

    @Single
    fun provideSensorService(retrofit: Retrofit): SensorService =
        retrofit.create(SensorService::class.java)

    @Single
    fun providePanelDao(db: MeceiotDataBase): PanelDao {
        return db.panelAlertDao()
    }

    @Single
    fun provideSensorDao(db: MeceiotDataBase): SensorDao {
        return db.sensorAlertDao()
    }



}