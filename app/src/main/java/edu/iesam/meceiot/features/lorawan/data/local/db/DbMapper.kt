package edu.iesam.meceiot.features.lorawan.data.local.db

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo
import java.util.Date


fun LoraWanInfo.toEntity(): LoraWanEntity =
    LoraWanEntity(this.id, this.title, this.image, this.description, date = Date())

fun LoraWanEntity.toDomain(): LoraWanInfo =
    LoraWanInfo(this.id, this.title, this.image, this.description)