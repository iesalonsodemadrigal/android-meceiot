package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDbLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.api.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.data.remote.firestore.DeveloperFirestoreRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val local: DeveloperDbLocalDataSource,
    private val remote: DeveloperApiRemoteDataSource
) : DeveloperRepository {
    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        val developersFromLocal = local.getAll()
        return if (developersFromLocal.isFailure) {
            remote.getDevelopers().onSuccess {
                local.saveAll(it)
            }
        } else {
            developersFromLocal
        }
    }
}