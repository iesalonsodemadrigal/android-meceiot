package edu.iesam.meceiot.features.externalresources.data.remote.firestore

import com.google.firebase.firestore.PropertyName

data class ExternalResourcesFirestoreModel(
    @get:PropertyName("idExternalResources") @set:PropertyName("idExternalResources") var idExternalResources: String = "",
    @get:PropertyName("author") @set:PropertyName("author") var author: String = "",
    @get:PropertyName("description") @set:PropertyName("description") var description: String = "",
    @get:PropertyName("image") @set:PropertyName("image") var image: String = "",
    @get:PropertyName("url") @set:PropertyName("url") var url: String = ""
)