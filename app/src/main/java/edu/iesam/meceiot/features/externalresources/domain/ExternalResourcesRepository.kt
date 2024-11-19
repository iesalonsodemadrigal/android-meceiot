package edu.iesam.meceiot.features.externalresources.domain

interface ExternalResourcesRepository {
    suspend fun getAllExternalResources(): Result<List<ExternalResources>>
}