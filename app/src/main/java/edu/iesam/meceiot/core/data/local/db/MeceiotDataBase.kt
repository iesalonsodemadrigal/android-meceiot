package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDao
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesEntity
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanEntity
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionEntity


@Database(
    entities = [LoraWanEntity::class, ExternalResourcesEntity::class, DeveloperEntity::class, QuestionEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {
    abstract fun loraWanDao(): LoraWanDao
    abstract fun externalResourcesDao(): ExternalResourcesDao
    abstract fun developerDao(): DeveloperDao
    abstract fun questionDao(): QuestionDao
}