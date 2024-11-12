package edu.iesam.meceiot.features.developer.domain.usecase

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo


interface DeveloperRepository {

    suspend fun getDevelopers(): List<DeveloperInfo>

}