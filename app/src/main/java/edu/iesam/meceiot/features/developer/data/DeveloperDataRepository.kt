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
        val localDeveloperResult = developerDbLocalDataSource.getAll()
        return localDeveloperResult.apply {
            onSuccess { localDevelopers ->
                Result.success(localDevelopers)
            }
            onFailure { localError ->
                if (localError is Exception) {
                    val remoteDeveloperResult = developerFirestoreRemoteDataSource.getDevelopers()
                    remoteDeveloperResult.onSuccess { remoteDevelopers ->
                        developerDbLocalDataSource.saveAll(remoteDevelopers)
                        return remoteDeveloperResult
                    }

                } else {
                    return Result.failure(localError)
                }
            }
        }
    }

}
