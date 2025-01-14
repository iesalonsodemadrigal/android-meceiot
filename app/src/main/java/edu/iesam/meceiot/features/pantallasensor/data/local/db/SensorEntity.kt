package edu.iesam.meceiot.features.pantallasensor.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val SENSOR_TABLE = "sensor"

@Entity(tableName = SENSOR_TABLE)
class SensorEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "sensorName") val nombre: String,
    @ColumnInfo(name = "panelName") val nombrePanel: String,
    @ColumnInfo(name = "values_x") val valoresX: List<Long>,
    @ColumnInfo(name = "values_y") val valoresY: List<Int>,
    @ColumnInfo(name = "leyend_x") val leyendaX: String,
    @ColumnInfo(name = "leyend_y") val leyendaY: String,
    @ColumnInfo(name = "createdAt") val date: Date
)

