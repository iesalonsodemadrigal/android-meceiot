package edu.iesam.meceiot.core.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {
    @TypeConverter
    fun fromLongList(value: List<Long>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toLongList(value: String?): List<Long>? {
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }
}