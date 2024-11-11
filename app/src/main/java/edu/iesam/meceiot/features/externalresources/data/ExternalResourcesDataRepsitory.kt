package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.ExternalResourcesXmlLocalDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesMockRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository

class ExternalResourcesDataRepsitory(
    private val remoteDataSource: ExternalResourcesMockRemoteDataSource,
    private val localDataSource: ExternalResourcesXmlLocalDataSource
) : ExternalResourcesRepository {
    override suspend fun getAllExternalResources(): List<ExternalResources> {
        val resourcesFromLocal = localDataSource.getExternalResources()
        return if (resourcesFromLocal.isEmpty()) {
            val resourcesFromRemote = remoteDataSource.getAllExternalResources()
            localDataSource.saveExternalResources(resourcesFromRemote)
            resourcesFromRemote
        } else {
            resourcesFromLocal
        }
    }
}



