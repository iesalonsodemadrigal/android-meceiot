package edu.iesam.meceiot.features.alerts.data.local.db

import edu.iesam.meceiot.features.alerts.domain.Alert
import edu.iesam.meceiot.features.alerts.domain.TypeSensor

fun Alert.toEntity(date: Long): AlertEntity {
    return AlertEntity(
        id = id,
        name = name,
        type = type.type,
        value = value,
        location = location,
        date = date
    )
}

fun AlertEntity.toDomain(): Alert {
    return Alert(
        id = id,
        name = name,
        type = TypeSensor.fromType(type),
        value = value,
        location = location
    )
} 