package edu.iesam.meceiot.core.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.iesam.meceiot.features.sensorpanels.domain.Sensor

class SensorListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSensorList(sensorList: List<Sensor>): String {
        return gson.toJson(sensorList)
    }

    @TypeConverter
    fun toSensorList(sensorListString: String): List<Sensor> {
        val listType = object : TypeToken<List<Sensor>>() {}.type
        return gson.fromJson(sensorListString, listType)
    }

    //hacer el sensoralertconverter
    /*
    @TypeConverter
    fun fromSensorList(sensorList: List<Sensor>): String {
        return gson.toJson(sensorList)
    }

    @TypeConverter
    fun toSensorList(sensorListString: String): List<Sensor> {
        return gson.fromJson(sensorListString, Array<Sensor>::class.java).toList()
    }
     */

}