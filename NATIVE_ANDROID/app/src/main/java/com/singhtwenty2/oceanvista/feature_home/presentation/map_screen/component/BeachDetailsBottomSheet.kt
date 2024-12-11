@file:OptIn(ExperimentalMaterial3Api::class)

package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.ConditionColor
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun BeachDetailsBottomSheet(
    beach: BeachColorCode?,
    onDismiss: () -> Unit
) {
    beach?.let { beachDetails ->
        val bottomSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = bottomSheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Beach Name and Condition with Enhanced Color Coding
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = beachDetails.name,
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(0.7f)
                    )
                    Box(
                        modifier = Modifier
                            .background(
                                color = getConditionColor(beachDetails.condition),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = beachDetails.condition.uppercase(),
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Condition Bulletin
                ConditionBulletin(beachDetails)

                Spacer(modifier = Modifier.height(16.dp))

                // Weather Details Grid
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

                Spacer(modifier = Modifier.height(16.dp))

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
                        icon = Icons.Rounded.LocationOn,
                        title = "Region",
                        value = beachDetails.region,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Enhanced Coordinates and Last Updated Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Precise Location",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${beachDetails.latitude}, ${beachDetails.longitude}",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Data Timestamp",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = formatLastUpdated(beachDetails.lastUpdated),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Close Beach Details", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun ConditionBulletin(beach: BeachColorCode) {
    val (bulletinText, bulletinColor, recommendationStyle) = getConditionBulletinDetails(beach)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = bulletinColor.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "Condition Warning",
                    tint = bulletinColor,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Beach Condition Bulletin",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = bulletinColor
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bulletinText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = recommendationStyle
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun getConditionBulletinDetails(beach: BeachColorCode): Triple<String, Color, FontWeight> {
    return when (mapConditionColorToDescription(beach)) {
        "excellent" -> Triple(
            """Optimal Beach Conditions
            • Pristine water clarity
            • Perfect wave conditions
            • Exceptional safety environment
            • Ideal for all water activities
            • Optimal swimming and surfing conditions
            • Minimal natural hazards detected""".trimMargin(),
            Color(0xFF2E8B57),  // Sea Green for excellent
            FontWeight.Normal
        )
        "moderate" -> Triple(
            """Cautionary Beach Conditions
            • Potential weather variability detected
            • Intermittent wave height fluctuations
            • Restricted water activity recommendations:
              - Experienced swimmers only
              - No children or novice swimmers
            • High wind speed potential
            • Mandatory pre-activity weather check
            • Enhanced vigilance required""".trimMargin(),
            Color(0xFFFFA500),  // Vibrant Orange for moderate
            FontWeight.SemiBold
        )
        "poor" -> Triple(
            """High-Risk Marine Conditions
            • Severe weather warnings in effect
            • Dangerous marine environment
            • Absolute restrictions on water activities:
              - No swimming
              - No water sports
              - Potential coastal hazards
            • Strong currents detected
            • Rough wave conditions
            • Immediate evacuation if already at beach""".trimMargin(),
            Color(0xFFDC143C),  // Crimson Red for poor
            FontWeight.Bold
        )
        "else" -> Triple(
            """Unaddressed Beach Conditions
            • Insufficient data for accurate assessment
            • Highest level of caution advised
            • Mandatory local marine forecast consultation
            • No water activities recommended
            • Professional marine report required before entry""".trimMargin(),
            MaterialTheme.colorScheme.secondary,
            FontWeight.Bold
        )
        else -> Triple(
            """Unknown Condition
            • Unpredictable beach environment
            • Exercise extreme caution
            • Consult local authorities
            • Avoid water activities
            • Seek additional information""".trimMargin(),
            MaterialTheme.colorScheme.error,
            FontWeight.Bold
        )
    }
}
@Composable
private fun getConditionColor(condition: String): Color {
    return when (condition.lowercase()) {
        "excellent" -> Color.Green
        "good" -> Color.Blue
        "moderate" -> Color(0xFFFFA500)
        "poor" -> Color.Red
        else -> MaterialTheme.colorScheme.secondary
    }
}

@Composable
private fun WeatherDetailCard(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

private fun formatLastUpdated(timestamp: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd, yyyy HH:mm a", Locale.getDefault())
        val date = inputFormat.parse(timestamp)
        date?.let { outputFormat.format(it) } ?: timestamp
    } catch (e: Exception) {
        timestamp
    }
}

fun mapConditionColorToDescription(beach: BeachColorCode): String {
    return when (beach.conditionColor) {
        ConditionColor.ORANGE -> "excellent"
        ConditionColor.YELLOW -> "moderate"
        ConditionColor.RED -> "poor"
        ConditionColor.GRAY -> "else"
        else -> "unknown"
    }
}