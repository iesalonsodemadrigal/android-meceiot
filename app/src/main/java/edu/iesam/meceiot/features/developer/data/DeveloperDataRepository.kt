package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.remote.api.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.data.remote.firestore.DeveloperFirestoreRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerFirestoreDataSource: DeveloperFirestoreRemoteDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource
) : DeveloperRepository {

//    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val localDevelopers: List<DeveloperFirestore> = developerFirestoreDataSource.getDevelopers().getOrDefault(emptyList())
//
//                if (localDevelopers.isEmpty()) {
//                    val remoteDevelopers = developerApiRemoteDataSource.getDevelopers()
//                    remoteDevelopers.onSuccess { developers ->
//                        developerFirestoreDataSource.saveAll(developers.map { it.toFirestore() })
//                    }
//                    remoteDevelopers
//                } else {
//                    Result.success(localDevelopers.map { it.toDomain() })
//                }
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
}
}
