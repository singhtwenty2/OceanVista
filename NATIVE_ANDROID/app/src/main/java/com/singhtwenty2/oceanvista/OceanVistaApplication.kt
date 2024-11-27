package com.singhtwenty2.oceanvista

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.singhtwenty2.oceanvista.feature_auth.di.authAppModule
import com.singhtwenty2.oceanvista.feature_home.di.homeAppModule
import com.singhtwenty2.oceanvista.feature_home.domain.worker.LocationWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class OceanVistaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OceanVistaApplication)
            modules(authAppModule, homeAppModule)
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "location",
                "Location",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
        scheduleLocationUpdates()
    }

    private fun scheduleLocationUpdates() {
        val workRequest = PeriodicWorkRequestBuilder<LocationWorker>(
            1,
            TimeUnit.MINUTES
        ).build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "LocationWorker",
                ExistingPeriodicWorkPolicy.UPDATE,
                workRequest
            )
    }
}