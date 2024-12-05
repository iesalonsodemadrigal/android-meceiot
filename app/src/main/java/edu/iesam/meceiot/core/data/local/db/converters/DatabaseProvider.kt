package edu.iesam.meceiot.core.data.local.db.converters

import android.content.Context
import androidx.room.Room
import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDblocalDataSource

object DatabaseProvider {

    private var database: MeceiotDataBase? = null

    fun provideDatabase(context: Context): MeceiotDataBase {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                MeceiotDataBase::class.java,
                "meceiot_database"
            ).build()
        }
        return database!!
    }

    fun provideQuestionDao(context: Context): QuestionDao {
        return provideDatabase(context).questionDao()
    }

    fun provideQuestionDblocalDataSource(context: Context): QuestionDblocalDataSource {
        return QuestionDblocalDataSource(provideQuestionDao(context))
    }
}