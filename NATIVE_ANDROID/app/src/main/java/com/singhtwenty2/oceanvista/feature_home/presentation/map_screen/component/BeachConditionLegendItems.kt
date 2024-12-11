package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.ConditionColor

@Composable
fun BeachConditionLegendItems() {
    val conditionColors = listOf(
        ConditionColor.YELLOW to "Favorable Conditions",
        ConditionColor.ORANGE to "Moderate Weather Risk",
        ConditionColor.RED to "High-Risk Weather",
        ConditionColor.GRAY to "Weather conditions not fully assessed. Recommend checking current local forecast."
    )

    conditionColors.forEach { (conditionColor, description) ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = when (conditionColor) {
                            ConditionColor.GREEN -> Color.Green
                            ConditionColor.YELLOW -> Color.Green
                            ConditionColor.ORANGE -> Color.Cyan
                            ConditionColor.RED -> Color.Red
                            ConditionColor.GRAY -> Color.Magenta
                        },
                        shape = CircleShape
                    )
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}