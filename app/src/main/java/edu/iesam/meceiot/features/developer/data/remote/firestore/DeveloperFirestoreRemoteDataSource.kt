package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.core.data.remote.firestoreCall
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class DeveloperFirestoreRemoteDataSource(private val firestore: FirebaseFirestore) {
    suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        val developersRemote = mutableListOf<DeveloperFirestoreModel>()
        return firestoreCall {
            firestore
                .collection("developers")
                .get()
                .await()
                .forEach { document ->
                    val remoteModel = document.toObject(DeveloperFirestoreModel::class.java)
                    developersRemote.add(remoteModel)
                }
            developersRemote.map { it.toModel() }
        }
    }
}