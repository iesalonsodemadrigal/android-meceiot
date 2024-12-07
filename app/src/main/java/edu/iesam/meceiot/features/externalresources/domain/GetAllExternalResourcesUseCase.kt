package edu.iesam.meceiot.features.externalresources.domain

import org.koin.core.annotation.Single

@Single
class GetAllExternalResourcesUseCase(private val externalResourcesRepository: ExternalResourcesRepository) {
    suspend operator fun invoke(): Result<List<ExternalResources>> {
        return externalResourcesRepository.getAllExternalResources()
    }

}