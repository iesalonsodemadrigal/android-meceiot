package edu.iesam.meceiot.features.externalresources.data.local.db

import edu.iesam.meceiot.features.externalresources.domain.ExternalResources
import java.util.Date

fun ExternalResources.toEntity(): ExternalResource =
    ExternalResource(
        id = 0,
        date = Date(),
        author = this.author,
        description = this.description,
        image = this.image,
        url = this.url
    )

fun ExternalResource.toDomain(): ExternalResources =
    ExternalResources(
        author = this.author,
        description = this.description,
        image = this.image,
        url = this.url
    )
