package edu.iesam.meceiot.features.developer.data


import edu.iesam.meceiot.features.developer.data.local.DeveloperXmlLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerXmlLocalDataSource: DeveloperXmlLocalDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource

) : DeveloperRepository {
    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        val developersFromLocal = developerXmlLocalDataSource.getDevelopers()
        if (developersFromLocal.isEmpty()) {
            val developersFromRemote = developerApiRemoteDataSource.getDevelopers()
            developerXmlLocalDataSource.saveAll(developersFromRemote)
            return Result.success(developersFromRemote)
        } else {
            return Result.success(developersFromLocal)
        }
    }


}