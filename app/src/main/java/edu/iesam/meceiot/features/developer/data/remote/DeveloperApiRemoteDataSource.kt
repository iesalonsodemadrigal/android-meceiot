package edu.iesam.meceiot.features.developer.data.remote


import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeveloperApiRemoteDataSource(private val developerApiService: DeveloperApiService) {


    suspend fun getDevelopers(): List<DeveloperInfo> {
        return withContext(Dispatchers.IO) {
            val response = developerApiService.getDevelopers()

            // Verificamos si la respuesta fue exitosa
            if (response.isSuccessful) {
                // response.body() es una lista directa en este caso
                val developerListItems = response.body() ?: emptyList()

                developerListItems.map { developerItem ->
                    DeveloperInfo(
                        id = developerItem.id ?: "ID desconocido", // Valor por defecto si es null
                        full_Name = developerItem.full_Name
                            ?: "Desconocido", // Valor por defecto si es null
                        college_Degree = developerItem.college_Degree
                            ?: "No especificado", // Valor por defecto si es null
                        url_Avatar = developerItem.url_Avatar
                            ?: "URL no disponible", // Valor por defecto si es null
                        url_Source = developerItem.url_Source
                            ?: "Fuente no disponible" // Valor por defecto si es null
                    )
                }

            } else {
                // Manejo de error si la respuesta no es exitosa
                emptyList() // Retorna una lista vacía en caso de error
            }
        }
    }


}



