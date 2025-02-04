package edu.iesam.meceiot.features.alerts.data.remote.firebase

import com.google.firebase.firestore.PropertyName

data class PanelAlertFirebaseModel(
    @PropertyName("id") val id: String = "",
    @PropertyName("name") val name: String = "",
    @PropertyName("sensors") val sensors: Map<String, SensorAlertFirebaseModel> = emptyMap()
)

data class SensorAlertFirebaseModel(
    @PropertyName("id") val id: String = "",
    @PropertyName("name") val name: String = "",
    @PropertyName("type") val type: String = "",
    @PropertyName("value") val value: String = ""
)