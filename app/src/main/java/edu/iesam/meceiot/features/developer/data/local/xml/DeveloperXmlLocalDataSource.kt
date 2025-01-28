package edu.iesam.meceiot.features.developer.data.local.xml


import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import org.koin.core.annotation.Single

@Single
class DeveloperFirestoreDataSource {
    private val db = Firebase.firestore

    /**
     * Guarda todos los desarrolladores en la colección "developers" de Firestore.
     * Sobrescribe los datos si ya existen.
     */
    fun saveAll(
        developerInfo: List<DeveloperInfo>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val batch = db.batch() // Usamos un batch para operaciones en lote.

        developerInfo.forEach { developer ->
            val documentRef = db.collection("developers").document(developer.id)
            batch.set(
                documentRef,
                developer
            ) // Sobrescribe el documento con los datos del desarrollador.
        }

        batch.commit()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    /**
     * Obtiene todos los desarrolladores desde la colección "developers".
     */
    fun getDevelopers(onSuccess: (List<DeveloperInfo>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("developers")
            .get()
            .addOnSuccessListener { result ->
                val developers = result.map { document ->
                    document.toObject(DeveloperInfo::class.java) // Convierte cada documento en un objeto DeveloperInfo.
                }
                onSuccess(developers)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}
