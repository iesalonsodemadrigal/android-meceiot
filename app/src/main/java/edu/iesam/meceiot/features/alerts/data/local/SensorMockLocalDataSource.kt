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

    /*
    private val mockSensors = Result.success(
        listOf(
            Sensor(
                id = "1",
                name = "Sensor de Movimiento - Entrada Principal",
                location = "Entrada Principal",
                movement = 0
            ),
            Sensor(
                id = "2",
                name = "Sensor de Movimiento - Oficina",
                location = "Oficina Central",
                movement = 1
            ),
            Sensor(
                id = "3",
                name = "Sensor de Movimiento - Almacén",
                location = "Almacén",
                movement = 0
            ),
            Sensor(
                id = "4",
                name = "Sensor de Movimiento - Pasillo",
                location = "Pasillo Norte",
                movement = 1
            )
        )
    )
*/
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
                    location = panel.location,
                    movement = panel.movement,
                    temperature = panel.temperature
                )
            }
            Result.success(sensors)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}