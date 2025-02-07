package edu.iesam.meceiot.features.pantallasensor.data.remote.firebase

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.pantallasensor.domain.GraphSensor
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class GraphSensorFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getSensorData(): Result<GraphSensor> {
        return try {
            val document = firestore.collection("graphSensor")
                .limit(1)
                .get()
                .await()
                .documents
                .firstOrNull()

            val sensor = document?.toObject(GraphSensor::class.java)
            if (sensor != null) {
                Result.success(sensor)
            } else {
                Result.failure(Exception("No sensor data found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}