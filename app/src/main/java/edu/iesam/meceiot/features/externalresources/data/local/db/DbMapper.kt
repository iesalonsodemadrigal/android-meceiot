package edu.iesam.meceiot.features.externalresources.data.local.db

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import java.util.Date

fun ExternalResources.toEntity(): ExternalResourcesEntity =
    ExternalResourcesEntity(
        date = Date(),
        author = this.author,
        description = this.description,
        image = this.image,
        url = this.url,
        idExternalResources = this.idExternalResources
    )

fun ExternalResourcesEntity.toDomain(): ExternalResources =
    ExternalResources(
        author = this.author,
        description = this.description,
        image = this.image,
        url = this.url,
        idExternalResources = this.idExternalResources
    )
