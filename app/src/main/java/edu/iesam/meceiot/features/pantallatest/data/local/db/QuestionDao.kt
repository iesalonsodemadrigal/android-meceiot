package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg questionEntity: QuestionEntity)

    @Query("SELECT * FROM question")
    suspend fun getAll(): List<QuestionEntity>
}