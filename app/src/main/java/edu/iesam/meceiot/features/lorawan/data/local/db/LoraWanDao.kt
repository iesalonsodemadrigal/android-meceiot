package edu.iesam.meceiot.features.lorawan.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.iesam.meceiot.core.domain.AppConstants.LORAWAN_ID
import edu.iesam.meceiot.core.domain.AppConstants.LORAWAN_TABLE

@Dao
interface LoraWanDao {

    //Métodos de busqueda
    @Query("SELECT * FROM $LORAWAN_TABLE")
    suspend fun getAll(): List<LoraWanEntity>

    @Query("SELECT * FROM $LORAWAN_TABLE WHERE $LORAWAN_ID = :id")
    suspend fun getById(id: String): LoraWanEntity

    @Query("DELETE FROM $LORAWAN_TABLE WHERE $LORAWAN_ID = :loraWanId")
    suspend fun deleteById(loraWanId: String)

    //Métodos de conveniencia
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg loraWanEntity: LoraWanEntity)
}