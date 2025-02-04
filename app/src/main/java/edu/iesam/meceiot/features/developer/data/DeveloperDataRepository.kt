package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDbLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.api.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.data.remote.firestore.DeveloperFirestoreRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerDbLocalDataSource: DeveloperDbLocalDataSource,
    private val developerFirestoreRemoteDataSource: DeveloperFirestoreRemoteDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource
) : DeveloperRepository {
    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        val localDevelopersResult = developerDbLocalDataSource.getAll()
        return if (localDevelopersResult.isFailure || localDevelopersResult.getOrNull()
                .isNullOrEmpty()
        ) {
            developerFirestoreRemoteDataSource.getDevelopers().onSuccess {
                developerDbLocalDataSource.saveAll(it)
            }
        } else {
            localDevelopersResult
        }

    }
}