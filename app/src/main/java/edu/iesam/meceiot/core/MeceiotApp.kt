package edu.iesam.meceiot.core

import android.app.Application
import com.google.firebase.FirebaseApp
import edu.iesam.meceiot.core.di.AppModule
import edu.iesam.meceiot.core.di.LocalModule
import edu.iesam.meceiot.core.di.RemoteModule
import edu.iesam.meceiot.features.alerts.di.SensorAlertModule
import edu.iesam.meceiot.features.developer.di.DeveloperModule
import edu.iesam.meceiot.features.externalresources.di.ExternalResourceModule
import edu.iesam.meceiot.features.grafana.di.GrafanaModule
import edu.iesam.meceiot.features.login.di.LoginModule
import edu.iesam.meceiot.features.pantallasensor.di.GraphSensorModule
import edu.iesam.meceiot.features.sensorpanels.di.SensorPanelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module


class MeceiotApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@MeceiotApp)
            modules(
                AppModule().module,
                RemoteModule().module,
                ExternalResourceModule().module,
                LocalModule().module,
                DeveloperModule().module,
                SensorPanelsModule().module,
                SensorAlertModule().module,
                GraphSensorModule().module,
                LoginModule().module,
                GrafanaModule().module
            )
        }
    }
}

