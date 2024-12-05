package edu.iesam.meceiot.features.externalresources.data.remote


import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import org.koin.core.annotation.Single

@Single
class ExternalResourcesRemoteDataSource(private val externalResourcesService: ExternalResourcesService) {
    suspend fun getAllExternalResources(): List<ExternalResources> {
        val response = externalResourcesService.getAllExternalResources()
        return if (response.isSuccessful) {
            response.body()?.map {
                it.toModel()
            } ?: emptyList()
        } else {
            emptyList()
        }
    }

}