package edu.iesam.meceiot.features.lorawan.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val LORAWAN_TABLE = "lorawan"
const val LORAWAN_ID = "lorawan_id"

@Entity(tableName = LORAWAN_TABLE)
class LoraWanEntity(
    @PrimaryKey @ColumnInfo(name = "LORAWAN_ID") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "save_info_date") val date: Date
)
