package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao,
    private val cacheCheck: CacheCheck
) {

    private val timeTTL: Long = 600000

    suspend fun getAll(): List<LoraWanInfo> {
        val validEntities = cacheCheck.execute(timeTTL)
        return validEntities.map { it.toDomain() }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}