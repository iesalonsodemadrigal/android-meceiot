package edu.iesam.meceiot.core.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import edu.iesam.meceiot.features.alerts.domain.Sensor

class SensorListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromSensorList(sensorList: List<Sensor>): String {
        return gson.toJson(sensorList)
    }

    @TypeConverter
    fun toSensorList(sensorListString: String): List<Sensor> {
        return gson.fromJson(sensorListString, Array<Sensor>::class.java).toList()
    }

}