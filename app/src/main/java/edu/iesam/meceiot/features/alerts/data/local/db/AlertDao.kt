package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlertDao {
    @Query("SELECT * FROM $ALERT_TABLE")
    suspend fun getAlerts(): List<AlertEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlerts(vararg alerts: AlertEntity)
} 