package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

const val SENSOR_TABLE = "sensor"
const val SENSOR_ID = "idSensor"

@Entity(tableName = SENSOR_TABLE)
class SensorEntity(
    @PrimaryKey @ColumnInfo(name = SENSOR_ID) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: TypeSensor,
    @ColumnInfo(name = "value") val value: String,
)