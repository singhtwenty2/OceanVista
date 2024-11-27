package com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.Beach

@Composable
fun FeaturedBeachCard(
    beach: Beach,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(280.dp)
            .fillMaxHeight()
            .padding(vertical = 8.dp)
            .clickable { },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = beach.photos.first(),
                contentDescription = "${beach.name} Beach Photo",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = beach.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = beach.region,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(4.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = null,
//                            tint = Color.Yellow,
//                            modifier = Modifier.size(16.dp)
//                        )
//                        Text(
//                            text = "4.5",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .background(Color.Transparent)
                    .align(Alignment.TopEnd),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.9f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Featured",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}