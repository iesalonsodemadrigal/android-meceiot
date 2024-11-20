package edu.iesam.meceiot.core.data.local.db.converters

import androidx.room.TypeConverter
import java.util.Date

class PowerConverter {
    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLongToDate(date: Long): Date {
        return Date(date)
    }
}