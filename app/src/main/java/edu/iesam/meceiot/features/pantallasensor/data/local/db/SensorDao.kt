package edu.iesam.meceiot.features.pantallasensor.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SensorDao {
    @Query("SELECT * FROM $SENSOR_TABLE")
    fun getAll(): List<SensorEntity>

    @Query("SELECT * FROM $SENSOR_TABLE WHERE id = :id")
    fun getById(id: Int): SensorEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg sensorEntity: SensorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sensorEntity: SensorEntity)
}