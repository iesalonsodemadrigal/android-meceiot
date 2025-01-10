package edu.iesam.meceiot.features.pantallasensor.data.local

import edu.iesam.meceiot.features.pantallasensor.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorMockLocalDataSource {

    fun getSensorData(): List<Sensor> {
        return listOf(
            Sensor(
                id = 1,
                nombre = "Temperature Sensor",
                nombrePanel = "Panel A",
                valoresX = listOf(23.4f, 24.0f, 22.8f),
                valoresY = listOf(1.0f, 2.0f, 3.0f),
                leyendaX = "Time",
                leyendaY = "Temperature"
            ),
            Sensor(
                id = 2,
                nombre = "Humidity Sensor",
                nombrePanel = "Panel B",
                valoresX = listOf(45.0f, 50.2f, 47.8f),
                valoresY = listOf(1.0f, 2.0f, 3.0f),
                leyendaX = "Time",
                leyendaY = "Humidity"
            )
        )
    }

}