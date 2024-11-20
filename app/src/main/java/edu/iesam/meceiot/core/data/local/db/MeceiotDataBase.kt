package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameEntity

@Database(entities = [QuestiongameEntity::class], version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {
    abstract fun QuestiongameDao(): QuestiongameDao
}