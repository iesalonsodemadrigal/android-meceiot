package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.data.remote.firestore.DeveloperFirestoreDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerFirestoreDataSource: DeveloperFirestoreDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource
) : DeveloperRepository {

    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        return withContext(Dispatchers.IO) {
            try {
                val localDevelopers = developerFirestoreDataSource.getAll()
                if (localDevelopers.isEmpty()) {
                    val remoteDevelopers = developerApiRemoteDataSource.getDevelopers()
                    remoteDevelopers.onSuccess { developers ->
                        developerFirestoreDataSource.saveAll(developers)
                    }
                    remoteDevelopers
                } else {
                    Result.success(localDevelopers)
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}