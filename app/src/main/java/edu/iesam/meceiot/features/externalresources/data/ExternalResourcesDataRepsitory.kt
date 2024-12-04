package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDbDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDataRepsitory(
    private val remoteDataSource: ExternalResourcesRemoteDataSource,
    private val localDataSource: ExternalResourcesDbDataSource
) : ExternalResourcesRepository {
    override suspend fun getAllExternalResources(): Result<List<ExternalResources>> {
        val localExternalResources = localDataSource.getExternalResources()
        return if (localExternalResources.isEmpty()) {
            val remoteExternalResources = remoteDataSource.getAllExternalResources()
            remoteExternalResources.map {
                localDataSource.saveExternalResources(it)
                it
            }
        } else {
            Result.success(localExternalResources)
        }
    }
}



