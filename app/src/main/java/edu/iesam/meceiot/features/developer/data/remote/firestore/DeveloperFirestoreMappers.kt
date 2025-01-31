package edu.iesam.meceiot.features.developer.data.remote.firestore

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo


fun DeveloperInfo.toFirestore(): DeveloperFirestore = DeveloperFirestore(
    id = id,
    name = fullName,
    urlAvatar = urlAvatar,
    urlSource = urlSource,
    collegeDegree = collegeDegree,

    )

fun DeveloperFirestore.toDomain(): DeveloperInfo = DeveloperInfo(
    id = id,
    fullName = name,
    urlAvatar = urlAvatar,
    urlSource = urlSource,
    collegeDegree = collegeDegree
)
