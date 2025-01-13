package edu.iesam.meceiot.features.setting.data.local.xml

import android.content.Context
import com.google.gson.Gson
import edu.iesam.meceiot.R

class SettingXmlLocalDataSource(private val context: Context) {
    private val gson = Gson()

    private val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.setting_filer_xml),
        Context.MODE_PRIVATE
    )

    fun saveAppVersion() {
        val editor = sharedPreferences.edit()
        editor.putString("app_version", getAppVersion())
        editor.apply()
    }

    fun getAppVersion(): String? {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }

    fun getAppVersionFromPrefs(): String? {
        return sharedPreferences.getString("app_version", null)
    }
}
