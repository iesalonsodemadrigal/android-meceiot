package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//@Database( version = 1, exportSchema = false)
@TypeConverters()
abstract class MeceiotDataBase : RoomDatabase() {

    //abstract fun loraWanDao(): LoraWanDao
    //abstract fun developerDao(): DeveloperDao
    //abstract fun testDao(): TestDao


}