package edu.iesam.meceiot.features.developer.data


import edu.iesam.meceiot.features.developer.data.local.DeveloperXmlLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.DeveloperMockRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.Developer
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository

class DeveloperDataRepository(
    private val developerMockRemoteDataSource: DeveloperMockRemoteDataSource
) : DeveloperRepository {
    override suspend fun getDevelopers(): List<Developer> {
        return developerMockRemoteDataSource.getDevelopers()
    }


}