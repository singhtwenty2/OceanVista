package com.singhtwenty2.oceanvista.feature_home.util

import kotlin.math.abs

fun convertToDMS(lat: Double, long: Double): Pair<String, String> {
    fun toDMS(coordinate: Double, isLatitude: Boolean): String {
        val direction = if (isLatitude) {
            if (coordinate >= 0) "N" else "S"
        } else {
            if (coordinate >= 0) "E" else "W"
        }

        val absolute = abs(coordinate)
        val degrees = absolute.toInt()
        val minutes = ((absolute - degrees) * 60).toInt()
        val seconds = ((absolute - degrees - minutes / 60.0) * 3600).toInt()

        return String.format("%d°%02d'%02d\"%s", degrees, minutes, seconds, direction)
    }

    val latDMS = toDMS(lat, isLatitude = true)
    val longDMS = toDMS(long, isLatitude = false)

    return Pair(latDMS, longDMS)
}