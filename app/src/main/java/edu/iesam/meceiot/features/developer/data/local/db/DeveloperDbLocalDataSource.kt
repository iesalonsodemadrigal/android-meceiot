package edu.iesam.meceiot.features.developer.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

const val TIME_DEVELOPER_TTL = 60000L // Tiempo límite para validar los datos

@Single
class DeveloperDbLocalDataSource(
    private val developerDao: DeveloperDao
) {

    suspend fun getAll(): Result<List<DeveloperInfo>> {
        val developerEntities = developerDao.getAll()

        val validEntities = developerEntities.filter { entity ->
            val timeDifference = System.currentTimeMillis() - entity.date.time
            timeDifference <= TIME_DEVELOPER_TTL
        }

        return if (validEntities.isEmpty()) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(developerEntities.map { it.toDomain() })
        }
    }

    suspend fun saveAll(developerInfo: List<DeveloperInfo>) {
        val developerInfoEntities = developerInfo.map { it.toEntity() }
        developerDao.saveAll(*developerInfoEntities.toTypedArray())
    }
}
