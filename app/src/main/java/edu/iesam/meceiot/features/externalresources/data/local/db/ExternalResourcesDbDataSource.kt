package edu.iesam.meceiot.features.externalresources.data.local.db

import edu.iesam.meceiot.core.domain.ErrorApp
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import org.koin.core.annotation.Single

const val TIME_EXTERNAL_RESOURCES = 60000L

@Single
class ExternalResourcesDbDataSource(private val externalResourcesDao: ExternalResourcesDao) {

    suspend fun getAll(): Result<List<ExternalResources>> {
        val externalResourcesEntities = externalResourcesDao.getAll()

        val validEntity = externalResourcesEntities.filter { entity ->
            val timeDifference = System.currentTimeMillis() - entity.date.time
            timeDifference <= TIME_EXTERNAL_RESOURCES
        }
        return if (validEntity.isEmpty()) {
            Result.failure(ErrorApp.DataExpiredError)
        } else {
            Result.success(externalResourcesEntities.map { it.toDomain() })
        }
    }

    suspend fun saveAll(externalResources: List<ExternalResources>) {
        val externalResourcesEntities = externalResources.map { it.toEntity() }
        externalResourcesDao.insertAll(*externalResourcesEntities.toTypedArray())
    }
}