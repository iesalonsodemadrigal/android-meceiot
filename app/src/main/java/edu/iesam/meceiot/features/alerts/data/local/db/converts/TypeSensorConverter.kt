package edu.iesam.meceiot.features.alerts.data.local.db.converts

import androidx.room.TypeConverter
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

class TypeSensorConverter {

    @TypeConverter
    fun fromTypeSensor(typeSensor: TypeSensor): String {
        return typeSensor.type
    }

    @TypeConverter
    fun toTypeSensor(type: String): TypeSensor {
        return TypeSensor.fromType(type)
    }
}