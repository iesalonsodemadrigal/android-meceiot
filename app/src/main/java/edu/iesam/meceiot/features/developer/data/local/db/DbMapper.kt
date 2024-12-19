package edu.iesam.meceiot.features.developer.data.local.db

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import java.util.Date


fun DeveloperInfo.toEntity(): DeveloperEntity =
    DeveloperEntity(
        id =  this.id,
        fullName = this.fullName,
        urlAvatar = this.urlAvatar,
        collegeDegree = this.collegeDegree,
        urlSource = this.urlSource,
        date = Date())

    fun DeveloperEntity.toDomain(): DeveloperInfo =
    DeveloperInfo(
        id =  this.id,
        fullName = this.fullName,
        urlAvatar = this.urlAvatar,
        collegeDegree = this.collegeDegree,
        urlSource = this.urlSource)