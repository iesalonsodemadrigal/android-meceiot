package edu.iesam.meceiot.features.alerts.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PanelAlertDao {

    @Query("SELECT * FROM $PANEL_TABLE")
    suspend fun getPanels(): List<PanelAlertEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePanels(vararg panels: PanelAlertEntity)
}