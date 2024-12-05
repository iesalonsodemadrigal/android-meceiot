package edu.iesam.meceiot.features.pantallatest.di

import edu.iesam.meceiot.core.data.local.db.MeceiotDataBase
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDblocalDataSource
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class QuestionModule {

    @Single
    fun provideQuestionDao(database: MeceiotDataBase): QuestionDao {
        return database.questionDao()
    }

    @Single
    fun provideQuestionDblocalDataSource(questionDao: QuestionDao): QuestionDblocalDataSource {
        return QuestionDblocalDataSource(questionDao)
    }
}