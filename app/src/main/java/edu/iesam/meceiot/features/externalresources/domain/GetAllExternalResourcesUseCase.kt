package edu.iesam.meceiot.features.externalresources.domain

class GetAllExternalResourcesUseCase(private val externalResourcesRepository: ExternalResourcesRepository) {
    suspend operator fun invoke(): List<ExternalResources> {
        return externalResourcesRepository.getAllExternalResources()
    }

}