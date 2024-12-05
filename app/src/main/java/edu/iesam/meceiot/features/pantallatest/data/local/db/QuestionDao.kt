package edu.iesam.meceiot.features.pantallatest.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getAllQuestions(): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE id = :id")
    fun getQuestionById(id: Int): QuestionEntity?

    @Insert
    fun insertQuestion(question: QuestionEntity)

    @Update
    fun updateQuestion(question: QuestionEntity)
}