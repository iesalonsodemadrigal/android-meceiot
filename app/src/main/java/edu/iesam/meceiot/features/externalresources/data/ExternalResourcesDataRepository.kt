package edu.iesam.meceiot.features.externalresources.data

import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDbDataSource
import edu.iesam.meceiot.features.externalresources.data.remote.firestore.ExternalResourcesFirestoreRemoteDataSource
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import edu.iesam.meceiot.features.externalresources.domain.ExternalResourcesRepository
import org.koin.core.annotation.Single

@Single
class ExternalResourcesDataRepository(
    private val local: ExternalResourcesDbDataSource,
    private val remote: ExternalResourcesFirestoreRemoteDataSource
) : ExternalResourcesRepository {
    override suspend fun getAllExternalResources(): Result<List<ExternalResources>> {
        val externalResourcesFromLocal = local.getAll()
        return if (externalResourcesFromLocal.isFailure) {
            remote.getExternalResources().onSuccess {
                local.saveAll(it)
            }
        } else {
            externalResourcesFromLocal
        }
    }
}



