package edu.iesam.meceiot.features.setting.data

import edu.iesam.meceiot.features.login.data.local.LoginXmlDataSource
import edu.iesam.meceiot.features.setting.domain.SettingsRepository
import edu.iesam.meceiot.features.setting.domain.User
import org.koin.core.annotation.Single

@Single
class SettingsDataRepository(
    private val local: LoginXmlDataSource
) : SettingsRepository {

    override fun getUser(): User {
        val name = local.getCredentials()
        return User(name = name?.user!!)
    }

}