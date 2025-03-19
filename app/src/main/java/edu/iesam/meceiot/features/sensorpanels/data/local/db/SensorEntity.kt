package edu.iesam.meceiot.features.sensorpanels.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val SENSOR_TABLE = "sensor"
const val SENSOR_ID = "idSensor"

@Entity(tableName = SENSOR_TABLE)
class SensorEntity(
    @PrimaryKey @ColumnInfo(name = SENSOR_ID) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "panelName") val panelName: String,
    @ColumnInfo(name = "query") val query: String
)