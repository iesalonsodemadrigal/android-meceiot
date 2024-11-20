package edu.iesam.meceiot.core.data.local.db.converters

import android.health.connect.datatypes.units.Power
import androidx.room.TypeConverter
import com.google.gson.Gson

class PowerConverter {
    @TypeConverter
    fun from(power: String): Power {
        return Gson().fromJson(power, Power::class.java)
    }

    @TypeConverter
    fun to(power: Power): String {
        return Gson().toJson(power, Power::class.java)
    }
}