package edu.iesam.meceiot.features.iot.data.local.xml

import android.content.Context
import com.google.gson.Gson
import edu.iesam.meceiot.features.IoT.domain.IoT
import org.koin.core.annotation.Single

@Single
class IotXmlLocalDataSource(private val context: Context) {
    private val sharedPref =
        context.getSharedPreferences("iot_data", Context.MODE_PRIVATE)

    private val gson = Gson()
    val editor = sharedPref.edit()

    fun saveIotData(iotData: List<IoT>) {
        iotData.forEach { iot ->
            editor.putString(iot.id, gson.toJson(iot))
        }
        editor.apply()
    }

    fun getIotData(): List<IoT> {
        val iotData = mutableListOf<IoT>()
        val mapData = sharedPref.all as Map<String, String>

        mapData.values.forEach {
            val data = gson.fromJson(it, IoT::class.java)
            iotData.add(data)
        }
        return iotData
    }
}