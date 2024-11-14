package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSnackbar(
    message: String,
    isVisible: Boolean,
    type: SnackbarType = SnackbarType.SUCCESS,
    onDismiss: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            color = when (type) {
                SnackbarType.SUCCESS -> MaterialTheme.colorScheme.primaryContainer
                SnackbarType.ERROR -> MaterialTheme.colorScheme.errorContainer
                SnackbarType.WARNING -> Color(0xFFFFF3E0)
            },
            tonalElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = when (type) {
                            SnackbarType.SUCCESS -> Icons.Rounded.CheckCircle
                            SnackbarType.ERROR -> Icons.Rounded.Error
                            SnackbarType.WARNING -> Icons.Rounded.Warning
                        },
                        contentDescription = null,
                        tint = when (type) {
                            SnackbarType.SUCCESS -> MaterialTheme.colorScheme.primary
                            SnackbarType.ERROR -> MaterialTheme.colorScheme.error
                            SnackbarType.WARNING -> Color(0xFFFF9800)
                        }
                    )
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = when (type) {
                            SnackbarType.SUCCESS -> MaterialTheme.colorScheme.onPrimaryContainer
                            SnackbarType.ERROR -> MaterialTheme.colorScheme.onErrorContainer
                            SnackbarType.WARNING -> Color(0xFF795548)
                        }
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Dismiss",
                        tint = when (type) {
                            SnackbarType.SUCCESS -> MaterialTheme.colorScheme.onPrimaryContainer
                            SnackbarType.ERROR -> MaterialTheme.colorScheme.onErrorContainer
                            SnackbarType.WARNING -> Color(0xFF795548)
                        }
                    )
                }
            }
        }
    }
}
