package edu.iesam.meceiot.features.pantallasensor.data.remote.firebase

import com.google.firebase.firestore.PropertyName

data class GraphSensorFirebaseModel(
 @PropertyName("id") val id: Int,
 @PropertyName("name") val name: String,
 @PropertyName("panelName") val panelName: String,
 @PropertyName ("dataType") val dataType: String,
 @PropertyName("xValues") val xValues: List<Long>,
 @PropertyName("yValues") val yValues: List<Int>,
 @PropertyName("maxValue") val maxValue: String,
 @PropertyName ("minValue") val minValue: String,
 @PropertyName ("avgValue") val avgValue: String,
 @PropertyName ("modeValue") val modeValue: String
)