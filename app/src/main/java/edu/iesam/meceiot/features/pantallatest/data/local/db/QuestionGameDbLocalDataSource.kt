package edu.iesam.meceiot.features.pantallatest.data.local.db

import edu.iesam.meceiot.features.pantallatest.domain.QuestionGame

class QuestionGameDbLocalDataSource(private val questionGameDao: QuestiongameDao) {

    suspend fun getAllQuestions(): List<QuestionGame> {
        return questionGameDao.findAll().map { it.toDomain() }
    }

    suspend fun getQuestionById(id: Int): QuestionGame? {
        return questionGameDao.findById(id)?.toDomain()
    }

    suspend fun insertQuestion(questionGame: QuestionGame) {
        questionGameDao.insert(questionGame.toEntity())
    }
}