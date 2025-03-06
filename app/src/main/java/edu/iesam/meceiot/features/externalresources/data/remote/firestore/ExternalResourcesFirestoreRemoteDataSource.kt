package edu.iesam.meceiot.features.externalresources.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.core.data.remote.firestoreCall
import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class ExternalResourcesFirestoreRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun getExternalResources(): Result<List<ExternalResources>> {
        val externalResourcesRemote = mutableListOf<ExternalResourcesFirestoreModel>()
        return firestoreCall {
            firestore
                .collection("externalResources")
                .get()
                .await()
                .forEach { document ->
                    val remoteModel = document.toObject(ExternalResourcesFirestoreModel::class.java)
                    externalResourcesRemote.add(remoteModel)
                }
            externalResourcesRemote.map { it.toModel() }
        }
    }
}
