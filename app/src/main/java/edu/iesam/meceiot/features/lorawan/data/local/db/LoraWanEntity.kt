package edu.iesam.meceiot.features.lorawan.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val LORAWAN_TABLE = "lorawan"
const val LORAWAN_ID = "lorawan_id"

@Entity(tableName = "LORAWAN_TABLE")
class LoraWanEntity(
    @PrimaryKey @ColumnInfo(name = "LORAWAN_ID") val id: String,
    @ColumnInfo(name = "LORAWAN_TITLE") val title: String,
    @ColumnInfo(name = "LORAWAN_IMAGE") val image: String,
    @ColumnInfo(name = "LORAWAN_DESCRIPTION") val description: String
)
