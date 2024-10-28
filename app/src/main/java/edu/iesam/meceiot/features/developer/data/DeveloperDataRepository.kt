package edu.iesam.meceiot.features.developer.data


import edu.iesam.meceiot.features.developer.data.local.DeveloperXmlLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository

class DeveloperDataRepository(
    private val developerXmlLocalDataSource: DeveloperXmlLocalDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource

) : DeveloperRepository {
    override suspend fun getDevelopers(): List<DeveloperInfo> {
        val developersFromLocal = developerXmlLocalDataSource.getDevelopers()
        if (developersFromLocal.isEmpty()) {
            val developersFromApi = developerApiRemoteDataSource.getDevelopers()
            developerXmlLocalDataSource.saveAll(developersFromApi)
            return developersFromApi
        } else {
            return developersFromLocal

        }


    }


}