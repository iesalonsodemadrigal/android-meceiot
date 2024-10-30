package edu.iesam.meceiot.features.developer.data.remote

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

fun DeveloperApiModel.toModel(): DeveloperInfo {
    return DeveloperInfo(
        this.id,
        this.full_Name,
        this.college_Degree,
        this.url_Avatar,
        this.url_Source

    )
}