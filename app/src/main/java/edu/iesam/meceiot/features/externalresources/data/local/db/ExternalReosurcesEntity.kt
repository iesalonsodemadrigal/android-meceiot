package edu.iesam.meceiot.features.externalresources.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val EXTERNAL_RESOURCES = "external_resources"

@Entity(tableName = EXTERNAL_RESOURCES)
class ExternalResourcesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "createdAt") val date: Date,
    @ColumnInfo val author: String,
    @ColumnInfo val description: String,
    @ColumnInfo val image: String,
    @ColumnInfo val url: String
)
