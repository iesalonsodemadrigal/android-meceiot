package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

const val TIME_LORAWAN_TTL = 60000L

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao,
) {

    suspend fun getAll(): List<LoraWanInfo> {
        val lastTime = System.currentTimeMillis()
        val loraWanInfoEntities = loraWanDao.getAll()

        loraWanInfoEntities.filter{ entity ->
            val timeDifferent = lastTime - entity.date.time
            timeDifferent <= TIME_LORAWAN_TTL
        }
        return loraWanInfoEntities.map { it.toDomain() }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}