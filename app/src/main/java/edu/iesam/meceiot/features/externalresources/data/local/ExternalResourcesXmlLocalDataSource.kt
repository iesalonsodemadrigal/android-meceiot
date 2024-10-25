package edu.iesam.meceiot.features.externalresources.data.local

import android.content.Context
import edu.iesam.meceiot.features.externalresources.domain.ExtrenalResources

class ExternalResourcesXmlLocalDataSource(private val context: Context) {
    private val sharedPref =
        context.getSharedPreferences("external_resources", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveAll(externalResources: List<ExtrenalResources>) {
        val editor = sharedPref.edit()
        externalResources.forEach { externalResources ->
            editor.putString(externalResources.resourceName, gson.toJson(externalResources))
        }
        editor.apply()
    }
    fun findAll(): List<ExtrenalResources> {
        val externalResources = mutableListOf<ExtrenalResources>()
        val allEntries = sharedPref.all
        allEntries.forEach { jsonExternalResources ->
            val externalResources = gson.fromJson(jsonExternalResources.value as String, ExtrenalResources::class.java)
            externalResources.add(externalResources)
        }
        return externalResources
    }
}