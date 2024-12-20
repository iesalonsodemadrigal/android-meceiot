package edu.iesam.meceiot.features.externalresources.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExternalResourcesDao {
    @Query("SELECT * FROM $EXTERNAL_RESOURCES_TABLE")
    suspend fun getAll(): List<ExternalResourcesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg externalResourcesEntity: ExternalResourcesEntity)
}