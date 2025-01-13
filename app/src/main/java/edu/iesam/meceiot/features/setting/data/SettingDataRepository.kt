package edu.iesam.meceiot.features.setting.data

import edu.iesam.meceiot.features.setting.data.local.xml.SettingXmlLocalDataSource

class SettingDataRepository(private val settingXmlLocalDataSource: SettingXmlLocalDataSource) {

    // Guarda la versión de la aplicación en SharedPreferences
    fun saveAppVersion() {
        settingXmlLocalDataSource.saveAppVersion()
    }

    // Obtiene la versión actual de la aplicación
    fun getAppVersion(): String? {
        return settingXmlLocalDataSource.getAppVersion()
    }

    // Recupera la versión de la aplicación desde SharedPreferences
    fun getAppVersionFromPrefs(): String? {
        return settingXmlLocalDataSource.getAppVersionFromPrefs()
    }
}
