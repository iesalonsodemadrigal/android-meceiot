package edu.iesam.meceiot.features.login.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.login.domain.LoginCredentials
import org.koin.core.annotation.Single

@Single
class LoginXmlDataSource(context: Context) {
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.app_name), Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun saveCredentials(credentials: LoginCredentials) {
        sharedPref.edit().putString("credentials", gson.toJson(credentials)).apply()
    }

    fun getCredentials(): LoginCredentials? {
        return sharedPref.getString("credentials", null)?.let { jsonCredentials ->
            gson.fromJson(jsonCredentials, LoginCredentials::class.java)
        }
    }

    fun deleteCredentials() {
        sharedPref.edit().remove("credentials").apply()
    }
}