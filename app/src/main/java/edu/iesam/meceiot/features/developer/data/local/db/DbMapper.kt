package edu.iesam.meceiot.features.developer.data.local.db

import edu.iesam.meceiot.features.developer.domain.models.DeveloperInfo
import java.util.Date


fun DeveloperInfo.toEntity(): DeveloperEntity =
    DeveloperEntity(this.id, this.fullName, this.urlAvatar, this.collegeDegree,this.urlSource, date = Date())

    fun DeveloperEntity.toDomain(): DeveloperInfo =
    DeveloperInfo(this.id, this.fullName, this.urlAvatar, this.collegeDegree,this.urlSource)