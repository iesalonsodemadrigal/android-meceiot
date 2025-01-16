package edu.iesam.meceiot.features.sensorpanels.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SensorDao {
    @Query("SELECT * FROM $SENSOR_TABLE")
    fun getSensors(): List<SensorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSensors(vararg sensors: SensorEntity)

}