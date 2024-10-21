package edu.iesam.meceiot.features.ExternalResources.domain

class GetAllExternalResourcesUseCase(private val externalResourcesRepository: ExternalResourcesRepository)  {
    operator fun invoke(): List<ExtrenalResources> {
        return externalResourcesRepository.getAllExternalResources()
    }

}