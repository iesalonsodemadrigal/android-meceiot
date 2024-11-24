package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import org.koin.core.annotation.Single

@Single
class CacheCheck(
    private val loraWanDao: LoraWanDao
) {
    suspend fun execute(time: Long): List<LoraWanEntity> {
        val currentTime = System.currentTimeMillis()
        val entities = loraWanDao.getAll()

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