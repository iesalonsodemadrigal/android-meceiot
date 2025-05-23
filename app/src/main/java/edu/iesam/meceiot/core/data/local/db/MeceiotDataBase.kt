package edu.iesam.meceiot.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.meceiot.BuildConfig
import edu.iesam.meceiot.core.data.local.db.converters.DateConverter
import edu.iesam.meceiot.core.data.local.db.converters.LongListConverter
import edu.iesam.meceiot.core.data.local.db.converters.SensorListConverter
import edu.iesam.meceiot.features.alerts.data.local.db.AlertDao
import edu.iesam.meceiot.features.alerts.data.local.db.AlertEntity
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperDao
import edu.iesam.meceiot.features.developer.data.local.db.DeveloperEntity
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesDao
import edu.iesam.meceiot.features.externalresources.data.local.db.ExternalResourcesEntity
import edu.iesam.meceiot.features.pantallasensor.data.local.db.GraphSensorDao
import edu.iesam.meceiot.features.pantallasensor.data.local.db.GraphSensorEntity
import edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.PanelEntity
import edu.iesam.meceiot.features.sensorpanels.data.local.db.SensorDao
import edu.iesam.meceiot.features.sensorpanels.data.local.db.SensorEntity


@Database(
    entities = [
        ExternalResourcesEntity::class,
        DeveloperEntity::class,
        SensorEntity::class,
        PanelEntity::class,
        GraphSensorEntity::class,
        AlertEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    SensorListConverter::class,
    LongListConverter::class
)
abstract class MeceiotDataBase : RoomDatabase() {
    abstract fun externalResourcesDao(): ExternalResourcesDao
    abstract fun developerDao(): DeveloperDao
    abstract fun panelDao(): PanelDao
    abstract fun sensorDao(): SensorDao
    abstract fun graphSensorDao(): GraphSensorDao
    abstract fun alertDao(): AlertDao
}