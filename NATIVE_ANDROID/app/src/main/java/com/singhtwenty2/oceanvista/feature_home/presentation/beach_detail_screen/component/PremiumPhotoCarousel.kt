package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlin.math.absoluteValue

@Composable
fun PremiumPhotoCarousel(photos: List<String>) {
    val pagerState = rememberPagerState(pageCount = { photos.size })

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)),
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Black.copy(alpha = 0.3f)
                            )
                        )
                    )
            ) {
                AsyncImage(
                    model = photos[page],
                    contentDescription = "Beach Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                            alpha = lerp(start = 0.7f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                            scaleX = lerp(start = 0.9f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                            scaleY = scaleX

                            rotationY = lerp(start = 15f, stop = 0f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
                        }
                        .blur(if (pagerState.currentPage != page) 4.dp else 0.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        ModernPageIndicator(
            pagerState = pagerState,
            pageCount = photos.size,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}