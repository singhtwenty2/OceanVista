package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun FiltersContent() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterSwitch("Excellent Beaches", false)
        FilterSwitch("Good Beaches", true)
        FilterSwitch("Moderate Beaches", true)
        FilterSwitch("Poor Beaches", false)
    }
}