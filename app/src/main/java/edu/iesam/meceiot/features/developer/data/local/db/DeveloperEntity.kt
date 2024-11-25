package edu.iesam.meceiot.features.developer.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


const val DEVELOPER_TABLE = "lorawan"
const val DEVELOPER_ID = "lorawan_id"

@Entity(tableName = DEVELOPER_TABLE)
class DeveloperEntity(
    @PrimaryKey @ColumnInfo(name = "DEVELOPER_ID") val id: String,
    @ColumnInfo(name = "nobre") val fullName: String,
    @ColumnInfo(name = "url_avatar") val urlAvatar: String,
    @ColumnInfo(name = "url_source") val urlSource: String,
    @ColumnInfo(name = "college_degree") val collegeDegree: String,
    @ColumnInfo(name = "last_date") val date: Date
)