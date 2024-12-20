package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDbDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.ExternalResourcesRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDataRepository(
    private val remoteDataSource: ExternalResourcesRemoteDataSource,
    private val localDataSource: ExternalResourcesDbDataSource
) : ExternalResourcesRepository {
    override suspend fun getAllExternalResources(): Result<List<ExternalResources>> {
        val localExternalResourcesResult = localDataSource.getAll()
        return if (localExternalResourcesResult.isFailure || localExternalResourcesResult.getOrNull()
                .isNullOrEmpty()
        ) {
            remoteDataSource.getAllExternalResources().onSuccess {
                localDataSource.saveAll(it)
            }
        } else {
            localExternalResourcesResult
        }
    }
}



