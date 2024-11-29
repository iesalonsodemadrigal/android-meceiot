package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.core.data.local.db.CacheCheck
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

const val LORAWAN_TTL = 60000L

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao,
    private val cacheCheck: CacheCheck<LoraWanDao>
) {

    suspend fun getAll(): List<LoraWanInfo> {
        //val loraWanInfoEntities = loraWanDao.getAll()
        val loraWanCache = cacheCheck.execute(LORAWAN_TTL)
        return loraWanCache.map { it.toDomain() }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}