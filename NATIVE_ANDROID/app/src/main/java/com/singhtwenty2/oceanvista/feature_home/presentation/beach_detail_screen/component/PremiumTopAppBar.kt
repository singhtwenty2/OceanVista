@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalMaterial3Api::class
)
package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PremiumTopAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    onBackPress: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (scrollBehavior.state.overlappedFraction > 0.5f)
            MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
        else Color.Transparent,
        label = "Background Color Animation"
    )

    val elevation by animateDpAsState(
        targetValue = if (scrollBehavior.state.overlappedFraction > 0.5f) 4.dp else 0.dp,
        label = "Elevation Animation"
    )

    val titleSize by animateFloatAsState(
        targetValue = if (scrollBehavior.state.overlappedFraction > 0.5f) 18f else 22f,
        label = "Title Size Animation"
    )

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = titleSize.sp,
                    letterSpacing = 0.5.sp
                )
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPress,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
                            )
                        )
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
                            )
                        )
                    )
                    .padding(4.dp)
            ) {
                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        onFavoriteToggle()
                    }
                ) {
                    AnimatedContent(
                        targetState = isFavorite,
                        transitionSpec = {
                            (fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                                    scaleIn(animationSpec = tween(220, delayMillis = 90), initialScale = 0.7f))
                                .with(
                                    fadeOut(animationSpec = tween(90)) +
                                            scaleOut(animationSpec = tween(90), targetScale = 0.7f)
                                )
                        },
                        label = "Favorite Icon Animation"
                    ) { favorite ->
                        if (favorite) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorite",
                                tint = Color.Red.copy(alpha = 0.8f),
                                modifier = Modifier.scale(1.2f)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            scrolledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
        ),
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background.copy(alpha = 0.9f),
                        MaterialTheme.colorScheme.background.copy(alpha = 0.7f)
                    )
                )
            )
            .shadow(elevation)
    )
}