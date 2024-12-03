package edu.iesam.meceiot.features.pantallatest.data.local.db

import edu.iesam.meceiot.features.pantallatest.domain.Question
import org.koin.core.annotation.Single

@Single
class QuestionDbLocalDataSource(private val questionDao: QuestionDao) {

    suspend fun getAll(): List<Question> {
        return questionDao.getAll().map { it.toDomain() }
    }

    suspend fun saveAll(questions: List<Question>) {
        questionDao.saveAll(*questions.map { it.toEntity() }.toTypedArray())
    }
}

