package edu.iesam.meceiot.features.developer.data.remote


import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

@Single
class DeveloperApiRemoteDataSource(private val developerApiService: DeveloperApiService) {

    suspend fun getDevelopers(): Result<List<DeveloperInfo>> {

        return apiCall { developerApiService.getDevelopers() }.map { developersApiModel ->
            developersApiModel.map { it.toModel() }

        }

    }
}