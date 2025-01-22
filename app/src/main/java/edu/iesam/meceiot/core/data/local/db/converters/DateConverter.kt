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

}