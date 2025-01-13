package edu.iesam.meceiot.features.pantallasensor.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SensorDao {
    @Query("SELECT * FROM $SENSOR_TABLE")
    suspend fun getAll(): List<SensorEntity>

    @Query("SELECT * FROM $SENSOR_TABLE WHERE id = :id")
    suspend fun getById(id: Int): SensorEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg sensorEntity: SensorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sensorEntity: SensorEntity)
}