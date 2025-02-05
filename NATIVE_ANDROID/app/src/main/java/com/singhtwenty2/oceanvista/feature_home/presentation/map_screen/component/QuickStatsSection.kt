package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.Waves
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode

@Composable
fun QuickStatsSection(beachDetails: BeachColorCode) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeatherDetailCard(
                icon = Icons.Rounded.Thermostat,
                title = "Temperature",
                value = "${beachDetails.temperature}°C",
                modifier = Modifier.weight(1f)
            )
            WeatherDetailCard(
                icon = Icons.Rounded.WaterDrop,
                title = "Humidity",
                value = "${beachDetails.humidity}%",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WeatherDetailCard(
                icon = Icons.Rounded.Air,
                title = "Wind Speed",
                value = "${beachDetails.windSpeed} km/h",
                modifier = Modifier.weight(1f)
            )
            WeatherDetailCard(
                icon = Icons.Rounded.Waves,
                title = "Wave Height",
                value = "100 m",
                modifier = Modifier.weight(1f)
            )
        }
    }
}