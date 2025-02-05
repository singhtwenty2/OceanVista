package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Grid(items: List<Triple<ImageVector, String, String>>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        for (i in items.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OceanMetricCard(
                    icon = items[i].first,
                    title = items[i].second,
                    value = items[i].third,
                    modifier = Modifier.weight(1f)
                )
                if (i + 1 < items.size) {
                    OceanMetricCard(
                        icon = items[i + 1].first,
                        title = items[i + 1].second,
                        value = items[i + 1].third,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}