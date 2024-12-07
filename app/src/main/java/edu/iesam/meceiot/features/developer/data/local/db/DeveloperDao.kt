package edu.iesam.meceiot.features.developer.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface  DeveloperDao {

    @Query("SELECT * FROM $DEVELOPER_TABLE")
    suspend fun getAll(): List<DeveloperEntity>

    @Query("SELECT * FROM $DEVELOPER_TABLE WHERE $DEVELOPER_ID = :id")
        suspend fun getById(id: String): DeveloperEntity?

    @Query("DELETE FROM $DEVELOPER_TABLE WHERE $DEVELOPER_ID = :developerId")
    suspend fun deleteById(developerId: String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg developerEntity: DeveloperEntity)
}