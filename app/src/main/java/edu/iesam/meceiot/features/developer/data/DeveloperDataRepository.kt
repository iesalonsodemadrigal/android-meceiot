package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDbLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerDbLocalDataSource: DeveloperDbLocalDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource
) : DeveloperRepository {

    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        val developersFromDbLocal = developerDbLocalDataSource.getAll()

        return if (developersFromDbLocal.isFailure) {
            developerApiRemoteDataSource.getDevelopers().onSuccess {
                developerDbLocalDataSource.saveAll(it)
            }
        } else {
            Result.success(developersFromDbLocal.getOrNull() ?: emptyList())
        }
    }
}