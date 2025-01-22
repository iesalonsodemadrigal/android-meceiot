package edu.iesam.meceiot.features.alerts.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.alerts.data.local.db.PanelAlertDao
import edu.iesam.meceiot.features.alerts.data.local.db.SensorAlertDao
import edu.iesam.meceiot.features.alerts.data.remote.SensorAlertService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


@Module
@ComponentScan
class SensorAlertModule {

    @Single
    fun provideSensorService(retrofit: Retrofit): SensorAlertService =
        retrofit.create(SensorAlertService::class.java)

    @Single
    fun providePanelDao(db: MeceiotDataBase): PanelAlertDao {
        return db.panelAlertDao()
    }

    @Single
    fun provideSensorDao(db: MeceiotDataBase): SensorAlertDao {
        return db.sensorAlertDao()
    }



}