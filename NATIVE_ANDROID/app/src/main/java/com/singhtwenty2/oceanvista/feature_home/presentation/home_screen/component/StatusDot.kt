package com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusDot(
    isOpen: Boolean = true,
    modifier: Modifier = Modifier
) {
    val color = if (isOpen) {
        Color.Green
    } else {
        Color.Red
    }

    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .scale(2f)
                .alpha(0.3f)
                .clip(CircleShape)
                .background(color)
        )
    }
}