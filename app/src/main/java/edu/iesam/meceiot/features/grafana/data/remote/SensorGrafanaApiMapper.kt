package edu.iesam.meceiot.features.grafana.data.remote

fun PanelGrafanaModel.toModel(): PanelModel {

    return PanelModel(this.id.toString(), this.uid, this.title)
}

data class PanelModel(
    val id: String,
    val uid: String,
    val name: String,

    )

/*

fun TargetsDashboardGrafanaModel.toModel(): Sensor {
    //No entiendo nada JAJJA, mañana pregunto a Miguel
}

fun PanelGrafanaModel.toPanel(sensors: List<Sensor>): Panel {
    return Panel(
        id = this.uid,
        name = this.title,
        sensors = sensors
    )

 */
