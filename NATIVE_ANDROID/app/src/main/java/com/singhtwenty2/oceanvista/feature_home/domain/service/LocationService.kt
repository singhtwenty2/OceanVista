package com.singhtwenty2.oceanvista.feature_home.domain.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import com.singhtwenty2.oceanvista.R
import com.singhtwenty2.oceanvista.feature_home.data.background.DefaultLocationClient
import com.singhtwenty2.oceanvista.feature_home.domain.background.LocationClient
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.request.UserLocationRequest
import com.singhtwenty2.oceanvista.feature_home.domain.repository.LocationRepository
import com.singhtwenty2.oceanvista.feature_home.util.HomeApiResponseHandler
import com.singhtwenty2.oceanvista.feature_home.util.convertToDMS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.get

class LocationService: Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient
    private val locationRepository: LocationRepository by inject()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
           ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Location Tracking Active")
            .setContentText("Initializing location updates...")
            .setSmallIcon(R.drawable.compose_multiplatform)
            .setOngoing(false)
            .setStyle(NotificationCompat.BigTextStyle().bigText("Tracking your location with precision."))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
//            .getLocationUpdates(1800000L)
            .getLocationUpdates(60 * 1000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude
                val long = location.longitude

                val response = locationRepository.updateLocation(
                    userLocationRequest = UserLocationRequest(
                        latitude = lat,
                        longitude = long
                    )
                )
                val (latDMS, longDMS) = convertToDMS(lat, long)
                val message: String
                val notificationContent: String
                when(response) {
                    is HomeApiResponseHandler.Success -> {
                        message = "Location updated successfully."
                        notificationContent = "Updated coordinates: $latDMS, $longDMS."
                    }
                    is HomeApiResponseHandler.BadRequest -> {
                        message = "Location update failed due to a bad request."
                        notificationContent = "Failed to update: $latDMS, $longDMS."
                    }
                    is HomeApiResponseHandler.Conflict -> {
                        message = "Location update encountered a conflict."
                        notificationContent = "Conflict at: $latDMS, $longDMS."
                    }
                    is HomeApiResponseHandler.InternalServerError -> {
                        message = "Server error occurred while updating location."
                        notificationContent = "Error for: $latDMS, $longDMS."
                    }
                    is HomeApiResponseHandler.UnAuthorized -> {
                        message = "Unauthorized to update location."
                        notificationContent = "Access denied for: $latDMS, $longDMS."
                    }
                    is HomeApiResponseHandler.UnknownError -> {
                        message = "Unknown error occurred while updating location."
                        notificationContent = "Error for: $latDMS, $longDMS."
                    }
                }
                val updatedNotification = notification.setContentText(notificationContent)
                    .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                notificationManager.notify(1, updatedNotification.build())
            }
            .launchIn(serviceScope)
        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel(message = "Service has been destroyed")
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}