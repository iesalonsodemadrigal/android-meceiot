package edu.iesam.meceiot.features.lorawan.data.remote

import edu.iesam.meceiot.features.lorawan.domain.LoraWanInfo

fun LoraWanApiModel.toModel(): LoraWanInfo {
    return LoraWanInfo(this.id, this.title, this.image, this.description)
}