package edu.iesam.meceiot.features.setting.domain

interface SettingRepository {
    abstract fun getAppVersion(): Result<AppVersion>
}