package edu.iesam.meceiot.features.externalresources.data.local

import android.content.Context
import com.google.gson.Gson
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
        sharedPref.all.forEach { (_, value) ->
            externalResources.add(gson.fromJson(value as String, ExtrenalResources::class.java))
        }
        return externalResources
    }
}