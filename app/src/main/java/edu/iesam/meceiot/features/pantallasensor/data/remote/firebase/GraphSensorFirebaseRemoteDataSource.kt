package edu.iesam.meceiot.features.pantallasensor.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class GraphSensorFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getSensorData(): Result<List<GraphSensor>> {
        return try {
            val graphSensorCollection = firestore.collection("graphSensor")
                .get()
                .await()

            val sensors = mutableListOf<GraphSensor>()

            for (document in graphSensorCollection.documents) {
                val sensor = document.toObject(GraphSensor::class.java)
                sensor?.let { sensors.add(it) }
            }
            Result.success(sensors)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}