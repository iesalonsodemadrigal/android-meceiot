package edu.iesam.meceiot.features.alerts.data.remote.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.alerts.domain.Sensor
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class SensorFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getSensors(): Result<List<Sensor>> {
        // Obtener todos los documentos de la colección "alerts"
        val querySnapshot = firestore.collection("alerts")
            .get()
            .await()

        // Lista mutable para almacenar los sensores
        val sensorsList = mutableListOf<Sensor>()

        // Iterar sobre todos los documentos de paneles
        for (documentSnapshot in querySnapshot.documents) {
            // Deserialización del documento
            val panel = documentSnapshot.toObject(PanelAlertFirebaseModel::class.java)
                ?: continue  // Si no se puede deserializar, continuar con el siguiente documento

            // Agregar los sensores de este panel a la lista
            sensorsList.addAll(panel.sensors.values.map { it.toDomain() })
        }

        // Verificar si se encontraron sensores
        if (sensorsList.isEmpty()) {
            return Result.failure(IllegalStateException("No se encontraron sensores en los paneles"))
        }

        Log.d("@dev", "Lista de sensores obtenida: $sensorsList")
        return Result.success(sensorsList)
    }
}