package edu.iesam.meceiot.features.pantallasensor.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GraphSensorDao {
    @Query("SELECT * FROM $SENSOR_TABLE")
    fun getAll(): List<GraphSensorEntity>

    @Query("SELECT * FROM $SENSOR_TABLE WHERE id = :id")
    fun getById(id: Int): GraphSensorEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg sensorEntity: GraphSensorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sensorEntity: GraphSensorEntity)
}