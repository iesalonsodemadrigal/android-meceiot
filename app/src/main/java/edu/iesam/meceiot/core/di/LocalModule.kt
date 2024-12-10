package edu.iesam.meceiot.core.di

import android.content.Context
import androidx.room.Room
import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class LocalModule {

    @Single
    fun provideDatabase(context: Context): MeceiotDataBase {
        val db = Room.databaseBuilder(
            context,
            MeceiotDataBase::class.java, "meceiot-db"
        )
        db.fallbackToDestructiveMigration()
        return db.build()

    }


}