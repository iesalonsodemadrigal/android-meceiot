package edu.iesam.meceiot.features.developer.data.remote.firestore

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class DeveloperFirestoreRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        return runCatching {
            firestore
                .collection("developers")
                .get()
                .await()
                .map { it.toObject(DeveloperFirestoreModel::class.java).toModel() }

        }

    }
}