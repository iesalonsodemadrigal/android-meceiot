package edu.iesam.meceiot.features.grafana.data.remote.mappers

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor
import edu.iesam.meceiot.features.alerts.domain.TypeSensor
import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.DashboardGrafanaModel
import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.DataFrameQueryResultDashboardGrafanaModel
import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.PanelGrafanaModel
import edu.iesam.meceiot.features.grafana.data.remote.grafanamodel.SensorDashboardGrafanaModel

// Convierte PanelGrafanaModel a Panel en dominio
fun PanelGrafanaModel.toPanel(sensors: List<Sensor>): Panel {
    return Panel(
        id = this.uid,
        name = this.title,
        sensors = sensors
    )
}

// Convierte SensorDashboardGrafanaModel a Sensor en dominio
fun SensorDashboardGrafanaModel.toSensor(dataFrame: DataFrameQueryResultDashboardGrafanaModel?): Sensor {
    val lastValue = dataFrame?.getLastValue()?.lastOrNull()?.toString() ?: "0"
    return Sensor(
        id = this.id.toString(),
        name = this.targets.firstOrNull()?.nameSensor ?: "Unknown",
        type = TypeSensor.fromType(this.targets.firstOrNull()?.query ?: ""),
        value = lastValue
    )
}

// Extrae los sensores de un DashboardGrafanaModel y los convierte a dominio
fun DashboardGrafanaModel.toSensors(dataFrames: Map<Int, DataFrameQueryResultDashboardGrafanaModel>): List<Sensor> {
    return this.panels.map { sensorPanel ->
        val dataFrame = dataFrames[sensorPanel.id] // Se busca el dato correspondiente
        sensorPanel.toSensor(dataFrame)
    }
}