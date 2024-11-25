package edu.iesam.meceiot.features.developer.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import org.koin.core.annotation.Single

@Single
class CacheCheck (private val developerDao: DeveloperDao)
 {
    suspend fun execute(time: Long): List<DeveloperEntity> {
        val currentTime = System.currentTimeMillis()
        val entities = developerDao.getAll()

        val expiredEntities = entities.filter {
            val timeDifference = currentTime - it.date.time
            timeDifference > time
        }

        if (expiredEntities.isNotEmpty()) {
            throw ErrorApp.DataExpiredErrorApp
        }
        return expiredEntities
    }
}