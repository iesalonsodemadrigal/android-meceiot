package edu.iesam.meceiot.features.alerts.data.local

import android.content.Context
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.Zone
import org.koin.core.annotation.Single

@Single
class SensorMockLocalDataSource(private val context: Context) {

    fun getSensor(): Result<List<Zone>> {
        val zones = listOf(
            Zone(
                id = "1",
                name = "Meceiot_Alonso_CO2_009 - A16",
                sensors = listOf(
                    Sensor(
                        id = "1",
                        name = "CO2_009",
                        description = "Sensor CO2 - clase A16",
                        type = "co2",
                        value = "420"
                    ),
                    Sensor(
                        id = "2",
                        name = "CO2_009",
                        description = "Sensor temperatura - clase A16",
                        type = "temp",
                        value = "10"
                    ),
                    Sensor(
                        id = "3",
                        name = "CO2_009",
                        description = "Sensor lux - clase A16",
                        type = "light",
                        value = "1000"
                    ),
                    Sensor(
                        id = "4",
                        name = "CO2_009",
                        description = "Sensor humedad - clase A16",
                        type = "hum",
                        value = "32"
                    ),
                    Sensor(
                        id = "5",
                        name = "CO2_009",
                        description = "Sensor movimiento - clase A16",
                        type = "mov",
                        value = "10"
                    )
                )
            ),
            Zone(
                id = "2",
                name = "Meceiot_Alonso_CO2_010 - C22",
                sensors = listOf(
                    Sensor(
                        id = "6",
                        name = "CO2_010",
                        description = "Sensor CO2 - clase C22",
                        type = "co2",
                        value = "420"
                    ),
                    Sensor(
                        id = "7",
                        name = "CO2_010",
                        description = "Sensor temperatura - clase C22",
                        type = "temp",
                        value = "15"
                    ),
                    Sensor(
                        id = "8",
                        name = "CO2_010",
                        description = "Sensor lux - clase C22",
                        type = "light",
                        value = "1000"
                    ),
                    Sensor(
                        id = "9",
                        name = "CO2_010",
                        description = "Sensor humedad - clase C22",
                        type = "hum",
                        value = "32"
                    ),
                    Sensor(
                        id = "10",
                        name = "CO2_010",
                        description = "Sensor movimiento - clase C22",
                        type = "mov",
                        value = "1"
                    )
                )
            ),
            Zone(
                id = "3",
                name = "Meceiot_Alonso_Sound_009 - Pasillo Pab A",
                sensors = listOf(
                    Sensor(
                        id = "11",
                        name = "Sound_009",
                        description = "Sensor sonido - Pab A",
                        type = "sound",
                        value = "60"
                    ),
                    Sensor(
                        id = "12",
                        name = "Sound_009",
                        description = "Sensor temperatura - Pab A",
                        type = "temp",
                        value = "20.45"
                    ),
                    Sensor(
                        id = "13",
                        name = "Sound_009",
                        description = "Sensor lux - Pab A",
                        type = "light",
                        value = "1000"
                    ),
                    Sensor(
                        id = "14",
                        name = "Sound_009",
                        description = "Sensor humedad - Pab A",
                        type = "hum",
                        value = "32"
                    ),
                    Sensor(
                        id = "15",
                        name = "Sound_009",
                        description = "Sensor movimiento - Pab A",
                        type = "mov",
                        value = "0"
                    )
                )
            ),
            Zone(
                id = "4",
                name = "Meceiot_Alonso_Sound_010 - Hall",
                sensors = listOf(
                    Sensor(
                        id = "16",
                        name = "Sound_010",
                        description = "Sensor sonido - Hall",
                        type = "sound",
                        value = "60"
                    ),
                    Sensor(
                        id = "17",
                        name = "Sound_010",
                        description = "Sensor temperatura - Hall",
                        type = "temp",
                        value = "20.45"
                    ),
                    Sensor(
                        id = "18",
                        name = "Sound_010",
                        description = "Sensor lux - Hall",
                        type = "light",
                        value = "1000"
                    ),
                    Sensor(
                        id = "19",
                        name = "Sound_010",
                        description = "Sensor humedad - Hall",
                        type = "hum",
                        value = "32"
                    ),
                    Sensor(
                        id = "20",
                        name = "Sound_010",
                        description = "Sensor movimiento - Hall",
                        type = "mov",
                        value = "1"
                    )
                )
            )
        )
        return Result.success(zones)
    }
}