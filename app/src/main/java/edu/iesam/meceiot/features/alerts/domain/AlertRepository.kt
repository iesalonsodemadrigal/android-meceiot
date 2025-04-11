package edu.iesam.meceiot.features.alerts.domain

interface AlertRepository {
    suspend fun getAlerts(): Result<List<Alert>>
} 