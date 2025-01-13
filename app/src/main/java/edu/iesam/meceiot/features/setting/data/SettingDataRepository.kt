package edu.iesam.meceiot.features.setting.data

import edu.iesam.meceiot.features.setting.data.local.xml.SettingXmlLocalDataSource

class SettingDataRepository(private val settingXmlLocalDataSource: SettingXmlLocalDataSource) {


    fun saveAppVersion() {
        settingXmlLocalDataSource.saveAppVersion()
    }


    fun getAppVersion(): String? {
        return settingXmlLocalDataSource.getAppVersion()
    }


    fun getAppVersionFromPrefs(): String? {
        return settingXmlLocalDataSource.getAppVersionFromPrefs()
    }
}
