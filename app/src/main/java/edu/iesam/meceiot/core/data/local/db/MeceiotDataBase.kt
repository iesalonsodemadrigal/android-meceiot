package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResource
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity

@Database(entities = [ExternalResource::class,DeveloperEntity::class], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {
    abstract fun externalResourcesDao(): ExternalResourcesDao
    abstract fun developerDao(): DeveloperDao
}