package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanDbLocalDataSource(private val loraWanDao: LoraWanDao) {

    suspend fun getAll(): List<LoraWanInfo> {
        return loraWanDao.getAll().map { it.toDomain() }
    }


}