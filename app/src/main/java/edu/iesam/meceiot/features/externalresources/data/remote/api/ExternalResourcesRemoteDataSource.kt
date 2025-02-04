package edu.iesam.meceiot.features.externalresources.data.remote.api


import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import org.koin.core.annotation.Single

@Single
class ExternalResourcesRemoteDataSource(private val externalResourcesService: ExternalResourcesService) {
    suspend fun getAllExternalResources(): Result<List<ExternalResources>> {
        return apiCall { externalResourcesService.getAllExternalResources() }.map{
            externalResources -> externalResources.map{ it.toModel() }
        }
    }

}