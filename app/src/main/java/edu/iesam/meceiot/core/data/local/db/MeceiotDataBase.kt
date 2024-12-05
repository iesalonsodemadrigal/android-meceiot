package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionEntity

@Database(entities = [QuestionEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}