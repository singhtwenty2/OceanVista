package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode

@Composable
fun ConditionBulletin(beach: BeachColorCode) {
    val (bulletinText, bulletinColor, recommendationStyle) = getConditionBulletinDetails(beach)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = bulletinColor.copy(alpha = 0.08f),
        shape = RoundedCornerShape(24.dp),
        tonalElevation = 2.dp,
        shadowElevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = bulletinColor.copy(alpha = 0.15f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "Maritime Status",
                        tint = bulletinColor,
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                    )
                }
                Text(
                    text = "Maritime Status Report",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    ),
                    color = bulletinColor
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = bulletinText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = recommendationStyle,
                    lineHeight = 24.sp,
                    letterSpacing = 0.25.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}