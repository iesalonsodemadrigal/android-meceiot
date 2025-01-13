package edu.iesam.meceiot.features.setting.domain

import org.koin.core.annotation.Single


@Single
class GetAppVersionUseCase (private val settingRepository: SettingRepository) {

    operator fun invoke(): Result<AppVersion> =
        settingRepository.getAppVersion()

}