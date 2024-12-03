package edu.iesam.meceiot.features.pantallatest.data


import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionGameDao
import edu.iesam.meceiot.features.pantallatest.domain.QuestionGame

class QuestionGameRepository(private val questionGameDao: QuestionGameDao) {

    suspend fun getAllQuestions(): List<QuestionGame> {
        return questionGameDao.getAllQuestions()
    }

    suspend fun insertQuestions(vararg questions: QuestionGame) {
        questionGameDao.insertQuestions(*questions)
    }
}