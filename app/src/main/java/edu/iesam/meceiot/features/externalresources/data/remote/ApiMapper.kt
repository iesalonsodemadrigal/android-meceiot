package edu.iesam.meceiot.features.externalresources.data.remote

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

fun ExternalResourcesApiModel.toModel(): ExternalResources {
    return ExternalResources(
        this.resourceName,
        this.resourceDescription,
        this.resourceImage,
        this.resourceUrl
    )

}