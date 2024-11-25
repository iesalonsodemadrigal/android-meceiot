package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity

@Database(entities = [DeveloperEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {


    abstract fun developerDao(): DeveloperDao

}