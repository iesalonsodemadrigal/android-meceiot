package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DeveloperFirestoreDataSource @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()
    private val developersCollection = firestore.collection("developers")

    suspend fun getAll(): List<DeveloperInfo> {
        val snapshot = developersCollection.get().await()
        return snapshot.documents.mapNotNull { document ->
            DeveloperInfo(
                id = document.id,
                fullName = document.getString("fullName") ?: "",
                urlAvatar = document.getString("urlAvatar") ?: "",
                collegeDegree = document.getString("collegeDegree") ?: "",
                urlSource = document.getString("urlSource") ?: ""
            )
        }
    }

    suspend fun saveAll(developers: List<DeveloperInfo>) {
        developers.forEach { developer ->
            developersCollection.document(developer.id).set(
                mapOf(
                    "fullName" to developer.fullName,
                    "urlAvatar" to developer.urlAvatar,
                    "collegeDegree" to developer.collegeDegree,
                    "urlSource" to developer.urlSource
                )
            ).await()
        }
    }

    suspend fun deleteById(id: String) {
        developersCollection.document(id).delete().await()
    }
}