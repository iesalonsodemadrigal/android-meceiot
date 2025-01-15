package edu.iesam.meceiot.features.alerts.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.meceiot.R
import edu.iesam.meceiot.features.alerts.domain.Panels
import edu.iesam.meceiot.features.alerts.domain.Sensor
import org.koin.core.annotation.Single
import java.io.IOException

@Single
class SensorMockLocalDataSource(private val context: Context) {

    private fun readJsonFromResRaw(): String {
        return context.resources.openRawResource(R.raw.panels).bufferedReader()
            .use { it.readText() }
    }

    fun getSensor(): Result<List<Sensor>> {
        return try {
            val json = readJsonFromResRaw()
            val panels = Gson().fromJson(json, Panels::class.java)

            val sensors = panels.panels.values.map { panel ->
                Sensor(
                    id = panel.id,
                    name = panel.name,
                    description = panel.description,
                    movement = panel.movement,
                )
            }
            Result.success(sensors)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}