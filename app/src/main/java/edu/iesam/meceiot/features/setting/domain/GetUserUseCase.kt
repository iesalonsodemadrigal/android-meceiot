package edu.iesam.meceiot.features.setting.domain

import org.koin.core.annotation.Single

@Single
class GetUserUseCase(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): User {
        return settingsRepository.getUser()
    }
}