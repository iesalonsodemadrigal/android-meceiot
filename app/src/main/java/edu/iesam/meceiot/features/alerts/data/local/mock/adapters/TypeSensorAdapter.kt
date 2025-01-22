package edu.iesam.meceiot.features.alerts.data.local.mock.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import edu.iesam.meceiot.features.alerts.domain.TypeSensor
import java.lang.reflect.Type

class TypeSensorAdapter : JsonDeserializer<TypeSensor> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TypeSensor {
        val type = json?.asString ?: ""
        return TypeSensor.fromType(type)
    }
}