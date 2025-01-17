package edu.iesam.meceiot.features.alerts.data.local

import android.content.Context
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor
import edu.iesam.meceiot.features.alerts.domain.Zone
import org.koin.core.annotation.Single

@Single
class SensorMockLocalDataSource(private val context: Context) {

    fun getSensor(): Result<List<Sensor>> {
        val zones = listOf(
            Zone(
                id = "1",
                name = "Meceiot_Alonso_CO2_009 - A16",
                sensors = listOf(
                    Sensor(
                        id = "1",
                        name = "Meceiot_Alonso_CO2_009 - A16",
                        description = "Sensor CO2 - clase A16",
                        type = TypeSensor.Co2,
                        value = "420"
                    ),
                    Sensor(
                        id = "2",
                        name = "Meceiot_Alonso_CO2_009 - A16",
                        description = "Temperatura crítica",
                        type = TypeSensor.Temperature,
                        value = "10"
                    ),
                    Sensor(
                        id = "3",
                        name = "Meceiot_Alonso_CO2_009 - A16",
                        description = "Luz encendida",
                        type = TypeSensor.Light,
                        value = "1000"
                    ),
                    Sensor(
                        id = "4",
                        name = "Meceiot_Alonso_CO2_009 - A16",
                        description = "Sensor humedad - clase A16",
                        type = TypeSensor.Humidity,
                        value = "31"
                    ),
                    Sensor(
                        id = "5",
                        name = "Meceiot_Alonso_CO2_009 - A16",
                        description = "Posible presencia de movimiento",
                        type = TypeSensor.Movement,
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
                        name = "Meceiot_Alonso_CO2_010 - C22",
                        description = "Niveles críticos de Co2",
                        type = TypeSensor.Co2,
                        value = "100"
                    ),
                    Sensor(
                        id = "7",
                        name = "Meceiot_Alonso_CO2_010 - C22",
                        description = "Temperatura crítica",
                        type = TypeSensor.Temperature,
                        value = "15"
                    ),
                    Sensor(
                        id = "8",
                        name = "Meceiot_Alonso_CO2_010 - C22",
                        description = "Luz encendida",
                        type = TypeSensor.Light,
                        value = "1000"
                    ),
                    Sensor(
                        id = "9",
                        name = "Meceiot_Alonso_CO2_010 - C22",
                        description = "Sensor humedad - clase C22",
                        type = TypeSensor.Humidity,
                        value = "33"
                    ),
                    Sensor(
                        id = "10",
                        name = "Meceiot_Alonso_CO2_010 - C22",
                        description = "Posible presencia de movimiento",
                        type = TypeSensor.Movement,
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
                        name = "Meceiot_Alonso_Sound_009 - Pasillo Pab A",
                        description = "Sensor sonido - Pab A",
                        type = TypeSensor.Sound,
                        value = "60"
                    ),
                    Sensor(
                        id = "12",
                        name = "Meceiot_Alonso_Sound_009 - Pasillo Pab A",
                        description = "Sensor temperatura - Pab A",
                        type = TypeSensor.Temperature,
                        value = "20.45"
                    ),
                    Sensor(
                        id = "13",
                        name = "Meceiot_Alonso_Sound_009 - Pasillo Pab A",
                        description = "Luz encendida",
                        type = TypeSensor.Light,
                        value = "1000"
                    ),
                    Sensor(
                        id = "14",
                        name = "Meceiot_Alonso_Sound_009 - Pasillo Pab A",
                        description = "Sensor humedad - Pab A",
                        type = TypeSensor.Humidity,
                        value = "40"
                    ),
                    Sensor(
                        id = "15",
                        name = "Meceiot_Alonso_Sound_009 - Pasillo Pab A",
                        description = "Posible presencia de movimiento",
                        type = TypeSensor.Movement,
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
                        name = "Meceiot_Alonso_Sound_010 - Hall",
                        description = "Sensor sonido - Hall",
                        type = TypeSensor.Sound,
                        value = "30"
                    ),
                    Sensor(
                        id = "17",
                        name = "Meceiot_Alonso_Sound_010 - Hall",
                        description = "Sensor temperatura - Hall",
                        type = TypeSensor.Temperature,
                        value = "20.45"
                    ),
                    Sensor(
                        id = "18",
                        name = "Meceiot_Alonso_Sound_010 - Hall",
                        description = "Luz encendida",
                        type = TypeSensor.Light,
                        value = "500"
                    ),
                    Sensor(
                        id = "19",
                        name = "Meceiot_Alonso_Sound_010 - Hall",
                        description = "Sensor humedad - Hall",
                        type = TypeSensor.Humidity,
                        value = "32"
                    ),
                    Sensor(
                        id = "20",
                        name = "Meceiot_Alonso_Sound_010 - Hall",
                        description = "Posible presencia de movimiento",
                        type = TypeSensor.Movement,
                        value = "1"
                    )
                )
            )
        )
        val allSensors = zones.flatMap { it.sensors }
        return Result.success(allSensors)
    }
}