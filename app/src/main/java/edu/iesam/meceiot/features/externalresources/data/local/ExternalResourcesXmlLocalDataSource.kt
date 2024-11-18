package edu.iesam.meceiot.features.externalresources.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import org.koin.core.annotation.Single

@Single
class ExternalResourcesXmlLocalDataSource(private val context: Context) {
    private val sharedPref =
        context.getSharedPreferences("external_resources", Context.MODE_PRIVATE)

    private val gson = Gson()
    val editor = sharedPref.edit()

    fun saveExternalResources(resources: List<ExternalResources>) {
        resources.forEach { externalResource ->
            editor.putString(externalResource.author, gson.toJson(externalResource))
        }
        editor.apply()

    }

    fun getExternalResources(): List<ExternalResources> {
        val externalResource = mutableListOf<ExternalResources>()
        val mapResources = sharedPref.all as Map<String, String>

        mapResources.values.forEach {
            val resource = gson.fromJson(it, ExternalResources::class.java)
            externalResource.add(resource)

        }
        return externalResource
    }

}