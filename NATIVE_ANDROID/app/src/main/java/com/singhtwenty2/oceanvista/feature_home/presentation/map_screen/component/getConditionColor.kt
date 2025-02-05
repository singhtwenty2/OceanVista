package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getConditionColor(condition: String): Color {
    return when (condition.lowercase()) {
        "excellent" -> Color.Green
        "good" -> Color.Blue
        "moderate" -> Color(0xFFFFA500)
        "poor" -> Color.Red
        else -> MaterialTheme.colorScheme.secondary
    }
}
