package edu.iesam.meceiot.features.alerts.data.local.mock

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.alerts.data.local.mock.adapters.TypeSensorAdapter
import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor
import org.koin.core.annotation.Single

@Single
class SensorMockLocalDataSource(private val context: Context) {

    private fun myGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(TypeSensor::class.java, TypeSensorAdapter())
            .create()
    }
    fun getSensor(): Result<List<Sensor>> {
        val inputStream = context.resources.openRawResource(R.raw.panels)
        val json = inputStream.bufferedReader().use { it.readText() }

        val gson = myGson()

        return try {
            val type = object : TypeToken<List<Panel>>() {}.type
            val panels: List<Panel> = gson.fromJson(json, type)

            val sensors = panels.flatMap { it.sensors }

            Result.success(sensors)
        } catch (e: JsonParseException) {
            Result.failure(e)
        }
    }
}