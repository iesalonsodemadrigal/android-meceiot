package edu.iesam.meceiot.features.pantallatest.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDao
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class QuestionModule {

    @Single
    fun provideQuestionDao(db: MeceiotDataBase): QuestionDao {
        return db.QuestionDao()
    }
}