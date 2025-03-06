package edu.iesam.meceiot.features.externalresources.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

const val EXTERNAL_RESOURCES_TABLE = "external_resources"

@Entity(tableName = EXTERNAL_RESOURCES_TABLE)
class ExternalResourcesEntity(
    @ColumnInfo(name = "createdAt") val date: Date,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "url") val url: String,
    @PrimaryKey @ColumnInfo(name = "id") val idExternalResources: String

)