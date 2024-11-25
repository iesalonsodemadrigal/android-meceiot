package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.core.domain.AppConstants.TIME_LORAWAN_TTL
import edu.iesam.meceiot.core.data.local.db.CacheCheck
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao,
    private val cacheCheck: CacheCheck
) {

    suspend fun getAll(): List<LoraWanInfo> {
        val validEntities = cacheCheck.execute(TIME_LORAWAN_TTL)
        return validEntities.map { it.toDomain() }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}