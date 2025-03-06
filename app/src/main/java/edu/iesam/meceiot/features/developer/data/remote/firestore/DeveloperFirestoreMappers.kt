package edu.iesam.meceiot.features.developer.data.remote.firestore

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo

fun DeveloperFirestoreModel.toModel(): DeveloperInfo {
    return DeveloperInfo(
        this.id.toString(),
        this.fullName,
        this.collegeDegree,
        this.urlAvatar,
        this.urlSource
    )
}