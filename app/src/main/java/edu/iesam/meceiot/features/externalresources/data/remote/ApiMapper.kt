package edu.iesam.meceiot.features.externalresources.data.remote

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources

fun ExternalResourcesApiModel.toModel(): ExternalResources {
    return ExternalResources(
        resourceName = author,
        resourceDescription = nameResource,
        resourceImage = iconSource,
        resourceUrl = linkResource
    )

}