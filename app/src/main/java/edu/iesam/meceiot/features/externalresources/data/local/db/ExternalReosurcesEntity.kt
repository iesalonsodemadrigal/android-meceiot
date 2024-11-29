package edu.iesam.meceiot.features.externalresources.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "external_resources")
data class ExternalResource(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val author: String,
    @ColumnInfo val description: String,
    @ColumnInfo val image: String,
    @ColumnInfo val url: String
)
