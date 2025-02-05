package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Science
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.Water
import androidx.compose.material.icons.rounded.WaterDamage
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OceanicDataSection(oceanicData: OceanicData) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Ocean Metrics",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Grid(
                items = listOf(
                    Triple(Icons.Rounded.Visibility, "Visibility", oceanicData.waterVisibility),
                    Triple(Icons.Rounded.Speed, "Current", oceanicData.currentStrength),
                    Triple(Icons.Rounded.WaterDrop, "Tidal Range", oceanicData.tidalRange),
                    Triple(Icons.Rounded.WaterDamage, "Quality", oceanicData.waterQuality),
                    Triple(Icons.Rounded.Thermostat, "Surface Temp", oceanicData.surfaceTemperature),
                    Triple(Icons.Rounded.Science, "pH Level", oceanicData.phLevel),
                    Triple(Icons.Rounded.Air, "Oxygen", oceanicData.dissolvedOxygen),
                    Triple(Icons.Rounded.Water, "Salinity", oceanicData.salinity)
                )
            )
        }
    }
}