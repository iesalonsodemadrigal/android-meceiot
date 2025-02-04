package edu.iesam.meceiot.features.alerts.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.alerts.domain.Sensor
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class SensorFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getSensors(): Result<List<Sensor>> {
        val alertsSensor = firestore.collection("alerts")
            .get()
            .await()

        val sensorsList = mutableListOf<Sensor>()

        for (document in alertsSensor.documents) {
            val panels = document.toObject(PanelAlertFirebaseModel::class.java)

            panels?.sensors?.values?.map { it.toDomain() }?.let { sensorsList.addAll(it) }
        }
        return Result.success(sensorsList)
    }
}