package edu.iesam.meceiot.features.lorawan.data.local

import android.content.Context
import android.util.Log
import com.example.android_meceiot.R
import com.google.gson.Gson
import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

class LoraWanXmlLocalDataSource(private val context: Context) {

    private val gson = Gson()

    private val sharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.lorawan_info_file),
            Context.MODE_PRIVATE
        )

    /** Solo he puesto las funciones que sirven para guardar, recuperar y borrar
     * el listado de información sobre `LoraWan` porque no tiene sentido mostrar solo
     * 1 curiosidad/información sobre `LoraWan` en la `view`
     */

    fun saveAll(loraWanInfos: List<LoraWanInfo>) {
        val editor = sharedPreferences.edit()
        loraWanInfos.forEach { loraWanInfo ->
            editor.putString(loraWanInfo.id, gson.toJson(loraWanInfo))
            Log.d("@dev", "Guardando: ${loraWanInfo.id}")
        }
        editor.apply()
        Log.d("@dev", "Datos guardados: ${loraWanInfos.size} items.")
    }


    fun getLoraWanInfo(): List<LoraWanInfo> {
        val loraWanInfos = mutableListOf<LoraWanInfo>()
        val mapLoraWanInfos = sharedPreferences.all
        mapLoraWanInfos.values.forEach { jsonLoraWanInfo ->
            val loraWanInfo = gson.fromJson(jsonLoraWanInfo as String, LoraWanInfo::class.java)
            loraWanInfos.add(loraWanInfo)
        }
        return loraWanInfos
    }

    fun deleteAll() {
        sharedPreferences.edit().clear().apply()
    }
}