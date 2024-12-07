package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

@Single
class GetDevelopersUseCase(private val developerRepository: DeveloperRepository) {

    suspend operator fun invoke(): Result<List<DeveloperInfo>> =
        developerRepository.getDevelopers().map { it.sortedBy { dev -> dev.id.toInt() } }
}
