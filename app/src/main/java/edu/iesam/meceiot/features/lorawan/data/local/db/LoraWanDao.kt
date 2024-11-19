package edu.iesam.meceiot.features.lorawan.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LoraWanDao {

    @Query("SELECT * FROM $LORAWAN_TABLE")
    suspend fun getAll(): List<LoraWanEntity>

    @Query("SELECT * FROM $LORAWAN_TABLE WHERE $LORAWAN_ID = :id")
    suspend fun getById(id: String): LoraWanEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(loraWanEntity: LoraWanEntity)

    @Update
    suspend fun updateLoraWan(vararg loraWanEntity: LoraWanEntity)

    @Delete
    suspend fun deleteAll(loraWanEntity: LoraWanEntity)

    @Query("DELETE FROM $LORAWAN_TABLE WHERE $LORAWAN_ID = :id")
    suspend fun deleteById(loraWanId: String)

}