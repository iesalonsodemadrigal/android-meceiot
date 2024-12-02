package edu.iesam.meceiot.core.data.local.db

class CacheCheck<T : Cache>(
    private val dao: GeneralDao<T>
) {
    fun execute(time: Long): List<T> {
        val currentTime = System.currentTimeMillis()
        val entities = dao.getAll()

        return entities.filter { entity ->
            val timeDifference = currentTime - entity.date.time
            timeDifference <= time
        }
    }
}

