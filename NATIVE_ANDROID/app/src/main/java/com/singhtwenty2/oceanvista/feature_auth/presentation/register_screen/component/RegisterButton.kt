package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RegisterButton(
    isEnabled: Boolean,
    onClick: () -> Unit,
    buttonText: String = RegisterScreenText.registerButtonText
) {
    val buttonScale by animateFloatAsState(
        targetValue = if (isEnabled) 1f else 0.98f,
        animationSpec = spring(dampingRatio = 0.7f), label = "",
    )
    val buttonElevation by animateDpAsState(
        targetValue = if (isEnabled) 4.dp else 0.dp,
        animationSpec = spring(dampingRatio = 0.7f), label = "",
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .scale(buttonScale)
            .clip(MaterialTheme.shapes.small),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            disabledContainerColor = Color.Transparent
        ),
        elevation = null
    ) {
        Text(
            text = buttonText,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}
