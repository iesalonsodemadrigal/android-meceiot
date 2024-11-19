package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.ExternalResourcesXmlLocalDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDataRepsitory(
    private val remoteDataSource: ExternalResourcesRemoteDataSource,
    private val localDataSource: ExternalResourcesXmlLocalDataSource
) : ExternalResourcesRepository {
    override suspend fun getAllExternalResources(): Result<List<ExternalResources>> {
        val resourcesFromLocal = localDataSource.getExternalResources()
        return if (resourcesFromLocal.isEmpty()) {
           remoteDataSource.getAllExternalResources().onSuccess {
                localDataSource.saveExternalResources(it)}
        } else {
            Result.success(resourcesFromLocal)
        }
    }
}





