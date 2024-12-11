package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfilePicture(imageUrl: String) {
    Surface(
        modifier = Modifier
            .size(140.dp)
            .border(
                BorderStroke(5.dp, Color(0xFF1A237E)),
                shape = CircleShape
            )
            .clip(CircleShape),
        shape = CircleShape
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}