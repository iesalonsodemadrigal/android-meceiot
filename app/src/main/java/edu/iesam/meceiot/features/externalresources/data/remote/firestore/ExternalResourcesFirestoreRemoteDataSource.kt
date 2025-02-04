package edu.iesam.meceiot.features.externalresources.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class ExternalResourcesFirestoreRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getExternalResources(): Result<List<ExternalResources>> {
        return runCatching {
            firestore
                .collection("externalResources")
                .get()
                .await()
                .map { it.toObject(ExternalResourcesFirestoreModel::class.java).toModel() }

        }

    }
}
