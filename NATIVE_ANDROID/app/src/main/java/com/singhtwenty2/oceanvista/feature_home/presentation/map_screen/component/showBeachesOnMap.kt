package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import android.annotation.SuppressLint
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.ConditionColor

@SuppressLint("PotentialBehaviorOverride")
fun showBeachesOnMap(
    googleMap: GoogleMap,
    beaches: List<BeachColorCode>,
    onMarkerClick: (BeachColorCode) -> Unit
) {
    for (beach in beaches) {
        val position = LatLng(beach.latitude, beach.longitude)
        val color = when (beach.conditionColor) {
            ConditionColor.GREEN -> BitmapDescriptorFactory.HUE_GREEN
            ConditionColor.YELLOW -> BitmapDescriptorFactory.HUE_YELLOW
            ConditionColor.ORANGE -> BitmapDescriptorFactory.HUE_CYAN
            ConditionColor.RED -> BitmapDescriptorFactory.HUE_RED
            ConditionColor.GRAY -> BitmapDescriptorFactory.HUE_MAGENTA
        }

        googleMap.addMarker(
            MarkerOptions()
                .position(position)
                .title(beach.name)
                .snippet(beach.condition)
                .icon(BitmapDescriptorFactory.defaultMarker(color))
        )?.tag = beach
    }

    googleMap.setOnMarkerClickListener { marker ->
        val beach = marker.tag as? BeachColorCode
        beach?.let { onMarkerClick(it) }
        true
    }

    if (beaches.isNotEmpty()) {
        val firstBeach = beaches.first()
        googleMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(12.58396, 77.572405),
                10f
            )
        )
    }
}