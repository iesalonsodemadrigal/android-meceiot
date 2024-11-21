package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestiongameDao {
    @Query("SELECT * FROM $Questiongame_table")
    suspend fun getAll(): List<QuestiongameEntity>

    @Query("SELECT * FROM $Questiongame_table WHERE $Questiongame_id = :id")
    suspend fun getByById(id: Int): QuestiongameEntity?


    @Query("DELETE FROM $Questiongame_table WHERE $Questiongame_id = :id")
    suspend fun deleteById(id: String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg questiongameEntity: QuestiongameEntity)
}