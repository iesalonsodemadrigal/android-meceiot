package edu.iesam.meceiot.features.pantallatest.di

import edu.iesam.meceiot.core.data.local.db.CacheCheck
import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestiongameDao
import org.koin.core.annotation.Single

class QuestionModule {

    @Single
    fun provideQuestionDao(db: MeceiotDataBase): QuestiongameDao {
        return db.QuestiongameDao()
    }

    @Single
    fun provideCacheCheck(dao: QuestiongameDao): CacheCheck{
        val time = 900000L
        return CacheCheck(time, dao)
    }
}