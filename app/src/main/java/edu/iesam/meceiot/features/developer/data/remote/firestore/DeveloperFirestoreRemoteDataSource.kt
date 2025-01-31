package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.developer.data.firestore.DeveloperFirestoreModel
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DeveloperFirestoreRemoteDataSource @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("developers")
    suspend fun getDevelopers(): List<DeveloperInfo> {
        return try {
            val querySnapshot = collection.get().await()
            querySnapshot.documents.mapNotNull { document ->

                document.toObject(DeveloperFirestoreModel::class.java)?.let { firestoreModel ->
                    DeveloperFirestoreMappers.toDomainModel(firestoreModel)
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}