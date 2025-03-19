package edu.iesam.meceiot.features.grafana.data.models.sensordata

// Representa la respuesta completa del endpoint
data class InfluxQueryResponseDto(
    val results: Map<String, InfluxQueryResultDto>
)

// Representa cada resultado en el objeto "results"
data class InfluxQueryResultDto(
    val frames: List<InfluxFrameDto>
)

// Representa cada frame que contiene la definición de la serie de tiempo y los datos
data class InfluxFrameDto(
    val schema: InfluxFrameSchemaDto,
    val data: InfluxFrameDataDto
)

// Representa el esquema de cada frame
data class InfluxFrameSchemaDto(
    val refId: String,
    val fields: List<InfluxFieldDto>
)

// Representa la definición de cada campo de la serie
data class InfluxFieldDto(
    val name: String,
    val type: String
)

// Representa los datos reales de cada frame, en este caso, "values" es una lista de listas
data class InfluxFrameDataDto(
    val values: List<List<Any>>
)