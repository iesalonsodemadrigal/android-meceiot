package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

const val SENSOR_ALERT_TABLE = "sensor_alert"
const val SENSOR_ALERT_ID = "sensor_alert_id"

@Entity(tableName = SENSOR_ALERT_TABLE)
class SensorAlertEntity(
    @PrimaryKey @ColumnInfo(name = SENSOR_ALERT_ID) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "type") val type: TypeSensor,
    @ColumnInfo(name = "value") val value: String,
)