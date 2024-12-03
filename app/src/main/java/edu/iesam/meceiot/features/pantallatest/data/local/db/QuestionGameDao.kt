package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.iesam.meceiot.features.pantallatest.domain.QuestionGame

@Dao
interface QuestionGameDao {
    @Query("SELECT * FROM question_game")
    suspend fun getAllQuestions(): List<QuestionGame>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(vararg questions: QuestionGame)
}