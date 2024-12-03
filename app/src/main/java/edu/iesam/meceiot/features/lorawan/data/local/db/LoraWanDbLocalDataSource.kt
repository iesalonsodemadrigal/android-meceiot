package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

const val TIME_LORAWAN_TTL = 60000L

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao,
) {

    suspend fun getAll(): Result<List<LoraWanInfo>> {
        val loraWanInfoEntities = loraWanDao.getAll()

        // 'all' garantiza que todas las entidades cumplan la condición TTL
        val validEntity = loraWanInfoEntities.all { entity ->
            val timeDifference = System.currentTimeMillis() - entity.date.time
            timeDifference <= TIME_LORAWAN_TTL
        }
        return if (validEntity) {
            Result.success(loraWanInfoEntities.map { it.toDomain() })
        } else {
            Result.failure(ErrorApp.DataExpiredError)
        }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}




