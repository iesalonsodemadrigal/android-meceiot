package edu.iesam.meceiot.core.data.local.db

import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//@Database(entities = [], version = 1, exportSchema = false)
@TypeConverters()
abstract class MeceiotDataBase : RoomDatabase() {

}