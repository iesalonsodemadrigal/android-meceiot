package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.core.data.remote.apiCall
import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.alerts.domain.TypeSensor
import edu.iesam.meceiot.features.grafana.data.models.dashboard.toPanel
import edu.iesam.meceiot.features.grafana.data.models.search.DashboardSummary
import edu.iesam.meceiot.features.grafana.data.models.search.toDashboardSummary
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryDto
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryRequestDto
import edu.iesam.meceiot.features.grafana.data.models.sensordata.InfluxQueryResponseDto
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import edu.iesam.meceiot.features.sensorpanels.domain.Panel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Single

@Single
class GrafanaRemoteDataSource(
    private val grafana: GrafanaService
) : GrafanaRemoteDataSourceInterface {

    override suspend fun searchDashboards(): Result<List<DashboardSummary>> {
        return apiCall { grafana.searchDashboards() }
            .fold(
                onSuccess = { dashboardDtos ->
                    Result.success(dashboardDtos.map { it.toDashboardSummary() })
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
    }

    override suspend fun getDashboardDetail(uid: String): Result<Panel> {
        return apiCall { grafana.getDashboardByUid(uid) }
            .fold(
                onSuccess = { detailDto ->
                    Result.success(detailDto.toPanel())
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
    }

    override suspend fun queryData(body: InfluxQueryRequestDto): Result<List<InfluxQueryResponseDto>> {
        return apiCall { grafana.queryData(body = body) }
            .fold(
                onSuccess = { response ->
                    Result.success(listOf(response))
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
    }

    override suspend fun getSensorPanels(): Result<List<Panel>> {
        // Paso 1: obtener listado de resúmenes
        return searchDashboards().fold(
            onSuccess = { summaries ->
                // Paso 2: para cada dashboard resumido, obtener el detalle (incluyendo sensores)
                val panels: List<Panel> = try {
                    coroutineScope {
                        summaries.map { summary ->
                            async {
                                // Llamamos al endpoint de detalle usando el uid
                                getDashboardDetail(summary.uid)
                                    .fold(
                                        onSuccess = { detailPanel ->
                                            // Si el detalle es correcto, usamos el Panel obtenido
                                            detailPanel
                                        },
                                        onFailure = { _ ->
                                            // En caso de fallo, devolvemos un Panel con sensores vacíos
                                            Panel(
                                                id = summary.id,
                                                name = summary.title,
                                                sensors = emptyList()
                                            )
                                        }
                                    )
                            }
                        }.awaitAll()
                    }
                } catch (e: Exception) {
                    // Si falla alguna de las llamadas concurrentes
                    return Result.failure(e)
                }
                // Paso 3: devolver la lista de paneles
                Result.success(panels)
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }

    override suspend fun getSensorData(query: String, from: Long, to: Long): Result<GraphSensor> {
        // Log para diagnóstico
        val queryHashCode = query.hashCode()
        android.util.Log.d(
            "GrafanaRemoteDS",
            "Getting sensor data for query (hash: $queryHashCode): $query"
        )
        
        // Create request object with query using the provided 'from' and 'to'
        val requestDto = InfluxQueryRequestDto(
            queries = listOf(
                InfluxQueryDto(
                    query = query,
                    refId = "A",
                    datasourceId = 4, // Default datasource ID for InfluxDB
                    intervalMs = 20000,
                    maxDataPoints = 1000
                )
            ),
            from = from.toString(),
            to = to.toString()
        )

        // Execute the query
        return queryData(requestDto).fold(
            onSuccess = { responses ->
                if (responses.isEmpty() || responses[0].results.isEmpty()) {
                    android.util.Log.e("GrafanaRemoteDS", "No data returned from query")
                    Result.failure(Exception("No data returned from query"))
                } else {
                    // Extract data from the first response
                    val result = responses[0].results["A"]
                        ?: return Result.failure(Exception("No results for reference A"))

                    if (result.frames.isEmpty()) {
                        android.util.Log.e("GrafanaRemoteDS", "No frames in result")
                        return Result.failure(Exception("No frames in result"))
                    }

                    // Extract values from the first frame
                    val frame = result.frames[0]
                    val values = frame.data.values

                    if (values.size < 2) {
                        android.util.Log.e("GrafanaRemoteDS", "Insufficient data in response")
                        return Result.failure(Exception("Insufficient data in response"))
                    }

                    // First list contains timestamps, second list contains values
                    val timestamps = values[0].mapNotNull { (it as? Double)?.toLong() }
                    val dataValues = values[1].mapNotNull { (it as? Double)?.toInt() }

                    if (timestamps.isEmpty() || dataValues.isEmpty()) {
                        android.util.Log.e("GrafanaRemoteDS", "Empty data series")
                        return Result.failure(Exception("Empty data series"))
                    }

                    // Calculate statistics
                    val max = dataValues.maxOrNull() ?: 0
                    val min = dataValues.minOrNull() ?: 0
                    val avg = dataValues.average().toInt()
                    val mode = dataValues.groupBy { it }
                        .maxByOrNull { it.value.size }?.key ?: 0

                    // Determine data type from query
                    val fieldPattern = Regex("""r\[["']_field["']\]\s*==\s*["']([^"']+)["']""")
                    val fieldMatch = fieldPattern.find(query)
                    val fieldName = fieldMatch?.groupValues?.getOrNull(1)

                    val dataType = when (fieldName) {
                        "temperature" -> "°C"
                        "humidity" -> "%"
                        "co2" -> "ppm"
                        "light" -> "lux"
                        "motion" -> "events"
                        "vdd" -> "mV"
                        "soundAvg" -> "dB"
                        "soundPeak" -> "dB"
                        else -> "units"
                    }

                    // Usamos el hash del query como ID consistente
                    val sensorId = queryHashCode
                    
                    // Create GraphSensor object
                    val graphSensor = GraphSensor(
                        id = sensorId, // Usamos el ID consistente
                        name = frame.schema.refId,
                        panelName = "Sensor Data",
                        dataType = dataType,
                        xValues = timestamps,
                        yValues = dataValues,
                        maxValue = "$max $dataType",
                        minValue = "$min $dataType",
                        avgValue = "$avg $dataType",
                        modeValue = "$mode $dataType"
                    )

                    android.util.Log.d(
                        "GrafanaRemoteDS",
                        "Sensor data retrieved successfully for ID=$sensorId, values size=${timestamps.size}"
                    )
                    Result.success(graphSensor)
                }
            },
            onFailure = { error ->
                android.util.Log.e(
                    "GrafanaRemoteDS",
                    "Error retrieving sensor data: ${error.message}"
                )
                Result.failure(error)
            }
        )
    }

    override suspend fun getSensorAlerts(): Result<List<Alert>> {
        // First, get all panels with their sensors
        return getSensorPanels().fold(
            onSuccess = { panels ->
                try {
                    coroutineScope {
                        // For each panel, get its sensors and transform them to alert sensors
                        val alertSensors = panels.flatMap { panel ->
                            panel.sensors.map { sensor ->
                                async {
                                    try {
                                        // Query data for this sensor
                                        // --- Need to define default 'from' and 'to' for alerts ---
                                        // --- Using the previous default (last 6 hours) for now --- 
                                        val toTime = System.currentTimeMillis()
                                        val fromTime =
                                            System.currentTimeMillis() - 6 * 60 * 60 * 1000
                                        val sensorDataResult =
                                            getSensorData(sensor.query, fromTime, toTime)
                                        sensorDataResult.fold(
                                            onSuccess = { graphSensor ->
                                                // Helper function to determine sensor type from name and query
                                                fun determineSensorType(
                                                    name: String,
                                                    query: String
                                                ): TypeSensor {
                                                    // Try to extract type from field filter pattern
                                                    val fieldPattern =
                                                        Regex("""r\[["']_field["']\]\s*==\s*["']([^"']+)["']""")
                                                    val fieldMatch = fieldPattern.find(query)
                                                    val fieldName =
                                                        fieldMatch?.groupValues?.getOrNull(1)
                                                            ?.lowercase()
                                                    return when {
                                                        fieldName == "temperature" || name.lowercase()
                                                            .contains("temp") -> TypeSensor.Temperature

                                                        fieldName == "humidity" || name.lowercase()
                                                            .contains("hum") -> TypeSensor.Humidity

                                                        fieldName == "co2" || name.lowercase()
                                                            .contains("co2") -> TypeSensor.Co2

                                                        fieldName == "light" || name.lowercase()
                                                            .contains("light") -> TypeSensor.Light

                                                        fieldName == "motion" || name.lowercase()
                                                            .contains("motion") -> TypeSensor.Movement

                                                        fieldName == "sound" || name.lowercase()
                                                            .contains("sound") -> TypeSensor.Sound

                                                        else -> TypeSensor.UnknownSensor
                                                    }
                                                }
                                                // Determine the sensor type based on the dataType
                                                val sensorType =
                                                    when (graphSensor.dataType.lowercase()) {
                                                        "°c" -> TypeSensor.Temperature
                                                        "%" -> TypeSensor.Humidity
                                                        "ppm" -> TypeSensor.Co2
                                                        "lux" -> TypeSensor.Light
                                                        "events" -> TypeSensor.Movement
                                                        "db" -> TypeSensor.Sound
                                                        else -> determineSensorType(
                                                            sensor.name,
                                                            sensor.query
                                                        )
                                                    }
                                                // Get the latest value
                                                val latestValue =
                                                    if (graphSensor.yValues.isNotEmpty()) {
                                                        graphSensor.yValues.last().toString()
                                                    } else "0"
                                                // Create the alert sensor
                                                Alert(
                                                    id = sensor.id.toString(),
                                                    name = sensor.name,
                                                    type = sensorType,
                                                    value = latestValue,
                                                    location = sensor.panelName
                                                )
                                            },
                                            onFailure = { null } // Skip sensors that fail to load
                                        )
                                    } catch (e: Exception) {
                                        null // Skip sensors that throw exceptions
                                    }
                                }
                            }
                        }
                        val results = alertSensors.awaitAll().filterNotNull()
                        Result.success(results)
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }
}
