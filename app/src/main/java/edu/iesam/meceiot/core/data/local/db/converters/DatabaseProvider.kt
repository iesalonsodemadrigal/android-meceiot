package edu.iesam.meceiot.core.data.local.db.converters

import android.content.Context
import androidx.room.Room
import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase

object DatabaseProvider {

    private var database: MeceiotDataBase? = null

    fun provideDatabase(context: Context): MeceiotDataBase {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                MeceiotDataBase::class.java,
                "meceiot-db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
        return database!!
    }
}