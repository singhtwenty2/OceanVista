package com.singhtwenty2.oceanvista.feature_home.domain.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices
import com.singhtwenty2.oceanvista.feature_home.data.background.DefaultLocationClient
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.request.UserLocationRequest
import com.singhtwenty2.oceanvista.feature_home.domain.repository.LocationRepository

class LocationWorker(
    private val appContext: Context,
    workerParams: WorkerParameters,
    private val locationRepository: LocationRepository
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val locationClient = DefaultLocationClient(
            appContext,
            LocationServices.getFusedLocationProviderClient(appContext)
        )

        locationClient.getLocationUpdates(60 * 1000L).collect {location ->
            locationRepository.updateLocation(
                userLocationRequest = UserLocationRequest(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )
        }
        return Result.success()
    }
}