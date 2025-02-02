package edu.iesam.meceiot.features.developer.data

import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDbLocalDataSource
import edu.iesam.meceiot.features.developer.data.remote.api.DeveloperApiRemoteDataSource
import edu.iesam.meceiot.features.developer.data.remote.firestore.DeveloperFirestoreRemoteDataSource
import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import edu.iesam.meceiot.features.developer.domain.usecase.DeveloperRepository
import org.koin.core.annotation.Single

@Single
class DeveloperDataRepository(
    private val developerDbLocalDataSource: DeveloperDbLocalDataSource,
    private val developerFirestoreRemoteDataSource: DeveloperFirestoreRemoteDataSource,
    private val developerApiRemoteDataSource: DeveloperApiRemoteDataSource
) : DeveloperRepository {

    override suspend fun getDevelopers(): Result<List<DeveloperInfo>> {
        // 1. Intenta obtener los datos locales
        return try {
            val localDevelopers = developerDbLocalDataSource.getAll().getOrThrow()
            if (localDevelopers.isNotEmpty()) {
                Result.success(localDevelopers) // Devuelve los datos locales si existen
            } else {
                // 2. Si no hay datos locales, intenta obtener los datos de la API
                try {
                    val apiDevelopers = developerApiRemoteDataSource.getDevelopers().getOrThrow()
                    developerDbLocalDataSource.saveAll(apiDevelopers) // Guarda los datos de la API localmente
                    Result.success(apiDevelopers)
                } catch (apiError: Exception) {
                    // 3. Si la API falla, intenta obtener los datos de Firestore
                    try {
                        val firestoreDevelopers =
                            developerFirestoreRemoteDataSource.getDevelopers().getOrThrow()
                        developerDbLocalDataSource.saveAll(firestoreDevelopers) // Guarda los datos de Firestore localmente
                        Result.success(firestoreDevelopers)
                    } catch (firestoreError: Exception) {

                        Result.failure(firestoreError)
                    }
                }
            }
        } catch (localError: Exception) {

            try {
                val apiDevelopers = developerApiRemoteDataSource.getDevelopers().getOrThrow()
                developerDbLocalDataSource.saveAll(apiDevelopers) // Guarda los datos de la API localmente
                Result.success(apiDevelopers)
            } catch (apiError: Exception) {

                try {
                    val firestoreDevelopers =
                        developerFirestoreRemoteDataSource.getDevelopers().getOrThrow()
                    developerDbLocalDataSource.saveAll(firestoreDevelopers) // Guarda los datos de Firestore localmente
                    Result.success(firestoreDevelopers)
                } catch (firestoreError: Exception) {

                    Result.failure(firestoreError)
                }
            }
        }
    }
}