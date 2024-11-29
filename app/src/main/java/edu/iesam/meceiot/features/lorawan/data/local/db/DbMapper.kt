package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo


fun LoraWanInfo.toEntity(): LoraWanEntity =
    LoraWanEntity(this.id, this.title, this.image, this.description)

fun LoraWanEntity.toDomain(): LoraWanInfo =
    LoraWanInfo(this.id, this.title, this.image, this.description)