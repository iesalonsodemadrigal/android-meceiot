package edu.iesam.meceiot.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestiongameDao {
    @Query("SELECT * FROM $Questiongame_table")
    suspend fun findAll(): List<QuestiongameEntity>

    @Query("SELECT * FROM $Questiongame_table WHERE $Questiongame_id = :id")
    suspend fun findById(id: Int): QuestiongameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionGame: QuestiongameEntity)
}