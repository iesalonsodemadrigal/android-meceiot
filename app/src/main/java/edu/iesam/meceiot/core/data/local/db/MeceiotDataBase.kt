package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.PowerConverter

@Database(entities = [QuestiongameEntity::class], version = 1, exportSchema = false)
@TypeConverters(PowerConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {

    abstract fun questionGameDao(): QuestiongameDao
}