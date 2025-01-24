package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.iesam.meceiot.features.alerts.domain.Sensor

const val PANEL_TABLE = "panel"
const val PANEL_ID = "idPanel"

@Entity(tableName = PANEL_TABLE)
class PanelEntity(
    @PrimaryKey @ColumnInfo(name = PANEL_ID) val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sensors") val sensors: List<Sensor>
)