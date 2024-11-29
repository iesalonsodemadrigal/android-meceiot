package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import org.koin.core.annotation.Single

@Single
class LoraWanDbLocalDataSource(private val loraWanDao: LoraWanDao) {

    suspend fun getAll(): List<LoraWanInfo> {
        val loraWanInfoEntities = loraWanDao.getAll()
        return loraWanInfoEntities.map { it.toDomain() }
    }

    suspend fun saveAll(loraWanInfo: List<LoraWanInfo>) {
        val loraWanInfoEntities = loraWanInfo.map { it.toEntity() }
        loraWanDao.saveAll(*loraWanInfoEntities.toTypedArray())
    }
}