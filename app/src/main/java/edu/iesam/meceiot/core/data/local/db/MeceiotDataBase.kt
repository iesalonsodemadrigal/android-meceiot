package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResource
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDao

@Database(entities = [ExternalResource::class], version = 1, exportSchema = false)
@TypeConverters()
abstract class MeceiotDataBase : RoomDatabase() {
 abstract fun externalResourcesDao(): ExternalResourcesDao
}