package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.core.data.local.db.CacheCheck
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

@Single
class LoraWanDbLocalDataSource(
    private val loraWanDao: LoraWanDao,
    private val cacheCheck: CacheCheck
) {
    /*
        suspend fun getAll(): List<LoraWanInfo> {
            val loraWanEntities = loraWanDao.getAll()
            val currentTime = System.currentTimeMillis()

            val validData = loraWanEntities.filter {
                val timeDifference = currentTime - it.date.time
                timeDifference <= 60000
            }
            return validData.map { it.toDomain() }
        }

     */

    suspend fun getAll(): List<LoraWanInfo> {
        val validEntities = cacheCheck.execute()
        return validEntities.map { it.toDomain() }
    }

    suspend fun getById(id: String): LoraWanInfo {
        return loraWanDao.getById(id).toDomain()
    }

    suspend fun delete(loraWanInfoId: String) {
        loraWanDao.deleteById(loraWanInfoId)
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}