package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.iesam.meceiot.features.alerts.domain.Sensor

const val PANEL_TABLE = "zone"
const val PANEL_ID = "zone_id"

@Entity(tableName = PANEL_TABLE)
class PanelAlertEntity(
    @PrimaryKey @ColumnInfo(name = PANEL_ID) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sensors") val sensors: List<Sensor>
)