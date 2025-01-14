package edu.iesam.meceiot.features.alerts.data.local

import android.content.Context
import edu.iesam.meceiot.features.alerts.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorMockLocalDataSource(private val context: Context) {


    fun getSensor(): Result<List<Sensor>> {
        return TODO()
    }
}