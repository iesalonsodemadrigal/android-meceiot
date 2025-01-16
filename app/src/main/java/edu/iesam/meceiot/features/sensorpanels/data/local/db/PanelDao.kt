package edu.iesam.meceiot.features.sensorpanels.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PanelDao {
    @Query("SELECT * FROM $PANEL_TABLE")
    fun getPanels(): List<PanelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePanels(vararg panels: PanelEntity)
}