package edu.iesam.meceiot.features.externalresources.data.local.db

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDbDataSource(private val externalResourcesDao: ExternalResourcesDao) {

    suspend fun getAll(): List<ExternalResources> {
        return externalResourcesDao.getAll().map { it.toDomain() }
    }

    suspend fun saveAll(externalResources: List<ExternalResources>) {
        val externalResourcesEntities = externalResources.map { it.toEntity() }
        externalResourcesDao.insertAll(*externalResourcesEntities.toTypedArray())
    }
}