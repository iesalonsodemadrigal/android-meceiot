package edu.iesam.meceiot.core.data.local.db

import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameEntity

class CacheCheck(
    private val time: Long,
    private val dao: QuestiongameDao
) {
    suspend fun execute(): List<QuestiongameEntity> {
        val currentTime = System.currentTimeMillis()
        val entities = dao.getAll()

        return entities.filter {
            val timeDifference = currentTime - it.date.time
            timeDifference <= time
        }
    }
}