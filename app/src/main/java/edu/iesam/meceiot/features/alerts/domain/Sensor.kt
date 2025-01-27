package edu.iesam.meceiot.features.alerts.domain

data class Panel(
    val id: String,
    val name: String,
    val sensors: List<Sensor>
)


data class Sensor(
    val id: String,
    val name: String,
    val type: TypeSensor,
    val value: String
)

sealed class TypeSensor(val type: String) {

    data object Co2 : TypeSensor(TYPE_CO2)
    data object Temperature : TypeSensor(TYPE_TEMP)
    data object Light : TypeSensor(TYPE_LIGHT)
    data object Humidity : TypeSensor(TYPE_HUMIDITY)
    data object Movement : TypeSensor(TYPE_MOVEMENT)
    data object Sound : TypeSensor(TYPE_SOUND)
    data object UnknownSensor : TypeSensor(UNKNOWN_TYPE)


    companion object {
        fun fromType(type: String): TypeSensor {
            return when (type) {
                TYPE_CO2 -> Co2
                TYPE_TEMP -> Temperature
                TYPE_LIGHT -> Light
                TYPE_HUMIDITY -> Humidity
                TYPE_MOVEMENT -> Movement
                TYPE_SOUND -> Sound
                else -> {
                    UnknownSensor
                }
            }
        }

        const val TYPE_CO2 = "co2"
        const val TYPE_TEMP = "temp"
        const val TYPE_LIGHT = "light"
        const val TYPE_HUMIDITY = "hum"
        const val TYPE_MOVEMENT = "mov"
        const val TYPE_SOUND = "sound"
        const val UNKNOWN_TYPE = ""

    }
}