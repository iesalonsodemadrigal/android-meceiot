package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanEntity

@Database(entities = [LoraWanEntity::class, DeveloperEntity::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {

    abstract fun loraWanDao(): LoraWanDao

    abstract fun developerDao(): DeveloperDao
}