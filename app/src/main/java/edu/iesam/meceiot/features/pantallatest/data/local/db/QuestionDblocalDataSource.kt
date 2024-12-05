package edu.iesam.meceiot.features.pantallatest.data.local.db

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionDblocalDataSource(private val questionDao: QuestionDao) {

    suspend fun getAllQuestions(): List<QuestionEntity> {
        return withContext(Dispatchers.IO) {
            questionDao.getAllQuestions()
        }
    }

    suspend fun insertOrUpdateQuestion(question: QuestionEntity) {
        withContext(Dispatchers.IO) {
            val existingQuestion = questionDao.getQuestionById(question.id)
            if (existingQuestion == null) {
                Log.d("QuestionDblocalDataSource", "Inserting question: $question")
                questionDao.insertQuestion(question)
            } else {
                Log.d("QuestionDblocalDataSource", "Updating question: $question")
                questionDao.updateQuestion(question)
            }
        }
    }

    suspend fun updateQuestion(question: QuestionEntity) {
        withContext(Dispatchers.IO) {
            questionDao.updateQuestion(question)
        }
    }
}