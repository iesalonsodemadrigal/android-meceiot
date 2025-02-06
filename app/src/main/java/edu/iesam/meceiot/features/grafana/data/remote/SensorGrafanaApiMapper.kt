package edu.iesam.meceiot.features.grafana.data.remote

import edu.iesam.meceiot.features.alerts.domain.Panel
import edu.iesam.meceiot.features.alerts.domain.Sensor

fun PanelGrafanaModel.toModel(): Panel {
    val sensors = TargetsDashboardGrafanaModel :List<Sensor>
    return Panel(this.id.toString(), this.title, sensors)
}

fun TargetsDashboardGrafanaModel.toModel(): Sensor {
    //No entiendo nada JAJJA, mañana pregunto a Miguel
}

fun PanelGrafanaModel.toPanel(sensors: List<Sensor>): Panel {
    return Panel(
        id = this.uid,
        name = this.title,
        sensors = sensors
    )
}