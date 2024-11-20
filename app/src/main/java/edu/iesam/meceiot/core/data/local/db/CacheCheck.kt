package edu.iesam.meceiot.core.data.local.db

import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanEntity

class CacheCheck(
    private val time: Long,
    private val dao: LoraWanDao
) {
    suspend fun execute(): List<LoraWanEntity> {
        val currentTime = System.currentTimeMillis()
        val entities = dao.getAll()

        return entities.filter {
            val timeDifference = currentTime - it.date.time
            timeDifference <= time
        }
    }
}