package edu.iesam.meceiot.features.pantallasensor.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val SENSOR_TABLE = "graph_sensor"

@Entity(tableName = SENSOR_TABLE)
class GraphSensorEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "sensorName") val nombre: String,
    @ColumnInfo(name = "panelName") val nombrePanel: String,
    @ColumnInfo(name = "dataType") val dataType: String,
    @ColumnInfo(name = "values_x") val valoresX: List<Long>,
    @ColumnInfo(name = "values_y") val valoresY: List<Int>,
    @ColumnInfo(name = "maxValue") val maxValue: String,
    @ColumnInfo(name = "minValue") val minValue: String,
    @ColumnInfo(name = "avgValue") val avgValue: String,
    @ColumnInfo(name = "modeValue") val modeValue: String,
    @ColumnInfo(name = "from_date") val fromDate: Long, // Rango de fecha inicio
    @ColumnInfo(name = "to_date") val toDate: Long,     // Rango de fecha fin
    @ColumnInfo(name = "createdAt") val date: Date
)

