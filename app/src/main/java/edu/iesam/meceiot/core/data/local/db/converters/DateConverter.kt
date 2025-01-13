package edu.iesam.meceiot.core.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(date: Long): Date {
        return Date(date)
    }

    @TypeConverter
    fun fromFloatList(value: List<Float>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toFloatList(value: String?): List<Float>? {
        val listType = object : TypeToken<List<Float>>() {}.type
        return Gson().fromJson(value, listType)
    }
}