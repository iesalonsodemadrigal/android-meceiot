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
        return try {
            val developersFromDbLocal = developerDbLocalDataSource.getAll()

            if (developersFromDbLocal.isSuccess && developersFromDbLocal.getOrNull()
                    ?.isNotEmpty() == true
            ) {

                Result.success(developersFromDbLocal.getOrNull() ?: emptyList())
            } else {

                val developersFromFirestore = developerFirestoreRemoteDataSource.getDevelopers()
                if (developersFromFirestore.isNotEmpty()) {

                    developerDbLocalDataSource.saveAll(developersFromFirestore)
                    Result.success(developersFromFirestore)
                } else {

                    val developersFromApi = developerApiRemoteDataSource.getDevelopers()
                    if (developersFromApi.isSuccess) {
                        developerDbLocalDataSource.saveAll(
                            developersFromApi.getOrNull() ?: emptyList()
                        )
                        Result.success(developersFromApi.getOrNull() ?: emptyList())
                    } else {
                        Result.failure(
                            developersFromApi.exceptionOrNull() ?: Exception("No data available")
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}