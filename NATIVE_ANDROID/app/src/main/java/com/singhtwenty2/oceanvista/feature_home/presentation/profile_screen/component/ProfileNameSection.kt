package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileNameSection(name: String, userName: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A237E)
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "@$userName",
        style = MaterialTheme.typography.bodyLarge.copy(
            color = Color.Gray
        )
    )
}