package edu.iesam.meceiot.core.data.local.db



import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanEntity
import org.koin.core.annotation.Single

@Single
class CacheCheck<T>(
    private val dao: Dao<T>
) {
    fun execute(time: Long): List<T> {
        val currentTime = System.currentTimeMillis()
        val entities = dao.getAll()

        val expiredEntities = entities.filter {
            val timeDifference = currentTime - it.date.time
            timeDifference > time
        }
        return entities
    }
}

interface Dao<T>{
    fun getAll(): List<T>
}