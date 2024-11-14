package com.singhtwenty2.oceanvista

import android.app.Application
import com.singhtwenty2.oceanvista.feature_auth.di.authAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OceanVistaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OceanVistaApplication)
            modules(authAppModule)
        }
    }
}