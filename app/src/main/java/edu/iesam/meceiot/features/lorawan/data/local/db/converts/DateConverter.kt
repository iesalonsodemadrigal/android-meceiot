package edu.iesam.meceiot.features.lorawan.data.local.db.converts

import androidx.room.TypeConverter
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