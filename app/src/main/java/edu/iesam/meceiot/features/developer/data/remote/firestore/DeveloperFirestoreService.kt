package edu.iesam.meceiot.features.developer.data.remote.firestore

interface DeveloperFirestoreService {
    suspend fun getDevelopers(): List<DeveloperFirestore>
}