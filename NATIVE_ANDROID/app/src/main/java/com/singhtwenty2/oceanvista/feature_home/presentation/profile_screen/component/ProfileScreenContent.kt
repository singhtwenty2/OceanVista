package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.ProfileState

@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    profile: ProfileState,
    onBackPressed: () -> Unit,
    onLogoutClicked: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 500),
        label = "Profile Card Scale"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1A237E),
                        Color(0xFF3F51B5)
                    )
                )
            )
            .padding(16.dp)
    ) {
        ProfileScreenHeader(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            onBackPressed = onBackPressed,
            onLogoutClicked = onLogoutClicked
        )
        ProfileCard(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .align(Alignment.Center)
                .clickable { isPressed = !isPressed },
            profile = profile
        )
    }
}