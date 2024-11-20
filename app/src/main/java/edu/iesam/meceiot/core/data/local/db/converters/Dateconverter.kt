package edu.iesam.meceiot.core.data.local.db

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}