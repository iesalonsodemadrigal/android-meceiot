package edu.iesam.meceiot.features.externalresources.data.local.db

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDbLocalDataSource(
    private val externalResourcesDao: ExternalResourcesDao
) {
    suspend fun getAll(): List<ExternalResources> {
    }
}