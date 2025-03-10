package edu.iesam.meceiot.features.developer.data.remote.api

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

fun DeveloperApiModel.toModel(): DeveloperInfo {
    return DeveloperInfo(
        this.id,
        this.fullName,
        this.collegeDegree,
        this.urlAvatar,
        this.urlSource

    )
}