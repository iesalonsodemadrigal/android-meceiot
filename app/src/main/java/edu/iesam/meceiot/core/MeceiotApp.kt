package edu.iesam.meceiot.core

import android.app.Application
import edu.iesam.meceiot.core.di.AppModule
import edu.iesam.meceiot.core.di.LocalModule
import edu.iesam.meceiot.core.di.RemoteModule
import edu.iesam.meceiot.features.lorawan.di.LoraWanModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module


class MeceiotApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MeceiotApp)
            modules(
                AppModule().module,
                RemoteModule().module,
                LocalModule().module,
                LoraWanModule().module
            )
        }
    }
}