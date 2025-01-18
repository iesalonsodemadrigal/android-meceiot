package edu.iesam.meceiot.features.sensorpanels.data.remote.mock

import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor
import org.koin.core.annotation.Single

@Single
class SensorPanelsMockRemoteDataSource {
    fun getSensorPanels(): Result<List<Panel>> {
        val sensorPanelList = listOf(
            Panel(
                id = 1,
                name = "Panel 1",
                sensors = listOf(
                    Sensor(
                        id = 1,
                        name = "Temperature"
                    ),
                    Sensor(
                        id = 2,
                        name = "Humidity"
                    ),
                    Sensor(
                        id = 3,
                        name = "CO2"
                    ),
                    Sensor(
                        id = 4,
                        name = "Motion"
                    ),
                    Sensor(
                        id = 5,
                        name = "Sensor 5"
                    )
                )
            ),
            Panel(
                id = 2,
                name = "Panel 2",
                sensors = listOf(
                    Sensor(
                        id = 3,
                        name = "Humidity"
                    ),
                    Sensor(
                        id = 4,
                        name = "CO2"
                    ),
                    Sensor(
                        id = 5,
                        name = "Sensor 5"
                    ),
                )
            ),
            Panel(
                id = 3,
                name = "Panel 3",
                sensors = listOf(
                    Sensor(
                        id = 1,
                        name = "Temperature"
                    )
                )
            ),
        )

        return Result.success(sensorPanelList)
    }
}