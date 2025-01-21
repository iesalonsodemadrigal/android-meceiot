package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val ZONE_TABLE = "zone"
const val ZONE_ID = "zone_id"

@Entity(tableName = ZONE_TABLE)
class ZoneAlertEntity(
    @PrimaryKey @ColumnInfo(name = ZONE_ID) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sensors") val sensors: List<SensorAlertEntity>
)