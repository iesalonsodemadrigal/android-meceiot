package edu.iesam.meceiot.features.externalresources.data.remote.firestore


import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

fun ExternalResourcesFirestoreModel.toModel(): ExternalResources {
    return ExternalResources(
        this.idExternalResources,
        this.author,
        this.description,
        this.image,
        this.url
    )
}