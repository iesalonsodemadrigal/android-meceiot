package edu.iesam.meceiot.features.pantallasensor.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.pantallasensor.data.local.db.GraphSensorDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class GraphSensorModule {

    @Single
    fun provideSensorDao(db: MeceiotDataBase): GraphSensorDao {
        return db.graphSensorDao()
    }
}