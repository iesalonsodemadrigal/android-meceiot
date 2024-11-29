package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanEntity

@Database(entities = [LoraWanEntity::class], version = 1, exportSchema = false)
@TypeConverters()
abstract class MeceiotDataBase : RoomDatabase() {

    abstract fun loraWanDao(): LoraWanDao

}