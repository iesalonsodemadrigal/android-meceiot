package edu.iesam.meceiot.features.pantallatest.data.local.db

import edu.iesam.meceiot.core.data.local.db.CacheCheck
import edu.iesam.meceiot.features.pantallatest.domain.QuestionGame
import org.koin.core.annotation.Single

@Single
class QuestionGameDbLocalDataSource(
    private val questionGameDao: QuestiongameDao,
    private val cacheCheck: CacheCheck) {

    suspend fun getAll(): List<QuestionGame> {
        val validEntities = cacheCheck.execute()
        return validEntities.map { it.toDomain() }
    }

    suspend fun saveAll(questionGames: List<QuestionGame>) {
        val questionGameEntities = questionGames.map { it.toEntity() }
        questionGameDao.saveAll(*questionGameEntities.toTypedArray())
    }
}