package edu.iesam.meceiot.core

import android.app.Application
import edu.iesam.meceiot.core.di.RemoteModule
import edu.iesam.meceiot.features.developer.di.DeveloperModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin



class MeceiotApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MeceiotApp)
            modules(
                AppModule().module,
                RemoteModule().module,
                DeveloperModule().module

            )
        }
    }
}