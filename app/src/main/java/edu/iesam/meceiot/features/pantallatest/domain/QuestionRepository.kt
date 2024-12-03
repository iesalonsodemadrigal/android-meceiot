package edu.iesam.meceiot.features.pantallatest.domain

interface QuestionRepository {
    suspend fun getQuestion(): Result<List<Question>>
}