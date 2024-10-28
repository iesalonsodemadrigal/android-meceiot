package edu.iesam.meceiot.features.developer.data.local

import android.content.Context
import com.example.android_meceiot.R
import com.google.gson.Gson
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

class DeveloperXmlLocalDataSource(private val context: Context) {
    private val gson = Gson()
    private val sharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.developer_filer_xml), Context.MODE_PRIVATE
        )

    fun saveAll(developerInfo: List<DeveloperInfo>) {
        val editor = sharedPreferences.edit()
        developerInfo.forEach {
            editor.putString(it.id, gson.toJson(it))

        }
        editor.apply()
    }

    fun getDevelopers(): List<DeveloperInfo> {
        val developerInfo = mutableListOf<DeveloperInfo>()
        val map = sharedPreferences.all
        map.forEach {
            developerInfo.add(gson.fromJson(it.value as String, DeveloperInfo::class.java))
        }
        return developerInfo
    }



}