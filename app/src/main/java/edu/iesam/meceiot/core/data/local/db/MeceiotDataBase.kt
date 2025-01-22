package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.core.data.local.db.converters.SensorListConverter
import edu.iesam.meceiot.features.alerts.data.local.db.PanelAlertDao
import edu.iesam.meceiot.features.alerts.data.local.db.PanelAlertEntity
import edu.iesam.meceiot.features.alerts.data.local.db.SensorAlertDao
import edu.iesam.meceiot.features.alerts.data.local.db.SensorAlertEntity
import edu.iesam.meceiot.features.alerts.data.local.db.converts.TypeSensorConverter
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDao
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesEntity
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanDao
import edu.iesam.meceiot.features.lorawan.data.local.db.LoraWanEntity
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionDao
import edu.iesam.meceiot.features.pantallatest.data.local.db.QuestionEntity


@Database(
    entities = [LoraWanEntity::class, ExternalResourcesEntity::class, DeveloperEntity::class,
        QuestionEntity::class, PanelAlertEntity::class, SensorAlertEntity::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(DateConverter::class, TypeSensorConverter::class, SensorListConverter::class)
abstract class MeceiotDataBase : RoomDatabase() {
    abstract fun loraWanDao(): LoraWanDao
    abstract fun externalResourcesDao(): ExternalResourcesDao
    abstract fun developerDao(): DeveloperDao
    abstract fun questionDao(): QuestionDao
    abstract fun panelAlertDao(): PanelAlertDao
    abstract fun sensorAlertDao(): SensorAlertDao
}