package edu.iesam.meceiot.features.developer.data.local.xml

import android.content.Context
import com.google.gson.Gson
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

@Single
class DeveloperXmlLocalDataSource(private val context: Context) {
    private val gson = Gson()

    private val sharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.developer_filer_xml),
            Context.MODE_PRIVATE
        )

    fun saveAll(developerInfo: List<DeveloperInfo>) {
        val editor = sharedPreferences.edit()
        developerInfo.forEach { developerInfo ->
            editor.putString(developerInfo.id, gson.toJson(developerInfo))
        }
        editor.apply()
    }

    fun getDevelopers(): List<DeveloperInfo> {
        val developerInfo = mutableListOf<DeveloperInfo>()
        val mapDeveloperInfo = sharedPreferences.all
        mapDeveloperInfo.values.forEach { developerInfoJson ->
            val developerInfoConf =
                gson.fromJson(developerInfoJson as String, DeveloperInfo::class.java)
            developerInfo.add(developerInfoConf)
        }
        return developerInfo


    }


}