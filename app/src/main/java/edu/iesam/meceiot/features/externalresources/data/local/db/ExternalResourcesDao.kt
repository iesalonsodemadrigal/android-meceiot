package edu.iesam.meceiot.features.externalresources.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExternalResourcesDao {
    @Query("SELECT * FROM external_resources")
    suspend fun getAll(): List<ExternalResource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg externalResources: ExternalResource)
}