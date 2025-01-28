package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.remote.firestore.DeveloperFirestoreDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerFirestoreDataSource: DeveloperFirestoreDataSource
) : DeveloperRepository {

    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        return try {
            val developers = developerFirestoreDataSource.getAll()
            Result.success(developers)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveDevelopers(developers: List<DeveloperInfo>) {
        developerFirestoreDataSource.saveAll(developers)
    }

    suspend fun deleteDeveloperById(id: String) {
        developerFirestoreDataSource.deleteById(id)
    }
}