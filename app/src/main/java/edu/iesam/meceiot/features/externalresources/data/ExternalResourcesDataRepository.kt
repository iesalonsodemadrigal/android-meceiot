package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDbDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.api.ExternalResourcesRemoteDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.firestore.ExternalResourcesFirestoreRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDataRepository(
    private val remoteDataSource: ExternalResourcesRemoteDataSource,
    private val localDataSource: ExternalResourcesDbDataSource,
    private val externalResourcesFirestoreRemoteDataSource: ExternalResourcesFirestoreRemoteDataSource

) : ExternalResourcesRepository {
    override suspend fun getAllExternalResources(): Result<List<ExternalResources>> {
        val localExternalResourcesResult = localDataSource.getAll()
        return if (localExternalResourcesResult.isFailure || localExternalResourcesResult.getOrNull()
                .isNullOrEmpty()
        ) {
            externalResourcesFirestoreRemoteDataSource.getExternalResources().onSuccess {
                localDataSource.saveAll(it)
            }
        } else {
            localExternalResourcesResult
        }

    }
}



