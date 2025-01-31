package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class DeveloperFirestoreRemoteDataSource {

    private val firestore = FirebaseFirestore.getInstance()
    private val developersCollection = firestore.collection("developers")

    suspend fun getDevelopers(): Result<List<DeveloperFirestore>> {
        return try {
            val snapshot = developersCollection.get().await()
            val developers = snapshot.toObjects<DeveloperFirestore>()
            Result.success(developers)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveAll(developers: List<DeveloperFirestore>) {
        developers.forEach { developer ->
            developersCollection.document(developer.id).set(developer).await()
        }
    }
}
