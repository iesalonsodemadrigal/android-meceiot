package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity

const val ALERT_TABLE = "alert"
const val ALERT_ID = "alertId"

@Entity(
    tableName = ALERT_TABLE,
    primaryKeys = [ALERT_ID, "location"]
)
data class AlertEntity(
    @ColumnInfo(name = ALERT_ID) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "date") val date: Long
)