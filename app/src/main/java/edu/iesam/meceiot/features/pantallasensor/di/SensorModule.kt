package edu.iesam.meceiot.features.pantallasensor.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.pantallasensor.data.SensorDataRepository
import edu.iesam.meceiot.features.pantallasensor.data.local.db.SensorDao
import edu.iesam.meceiot.features.pantallasensor.data.remote.SensorRemoteMockDataSource
import edu.iesam.meceiot.features.pantallasensor.domain.GetSensorDataUseCase
import edu.iesam.meceiot.features.pantallasensor.domain.SensorRepository
import edu.iesam.meceiot.features.pantallasensor.presentation.SensorViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@Module
@ComponentScan
class SensorModule {
    val sensorModule = module {
        single { SensorRemoteMockDataSource() }
        single<SensorRepository> { SensorDataRepository(get(), get()) }
        single { GetSensorDataUseCase(get()) }
        viewModel { SensorViewModel(get()) }
    }

    @Single
    fun provideSensorDao(db: MeceiotDataBase): SensorDao {
        return db.sensorDao()
    }
}