@file:OptIn(ExperimentalMaterial3Api::class)

package com.singhtwenty2.oceanvista.feature_home.presentation.map_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_home.domain.model.dto.response.BeachColorCode
import com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component.BeachDetailsBottomSheet
import com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component.EnhancedGoogleMap
import com.singhtwenty2.oceanvista.feature_home.presentation.map_screen.component.MapControls

@Composable
fun MapScreenComposable(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel
) {
    val beaches by viewModel.beaches.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val selectedBeach = remember { mutableStateOf<BeachColorCode?>(null) }
    val mapControls = remember { mutableStateOf(MapControls()) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("OceanVista", style = MaterialTheme.typography.headlineMedium) },
                actions = {
                    IconButton(
                        onClick = {
                            mapControls.value = mapControls.value.copy(
                                isFilterOpen = !mapControls.value.isFilterOpen
                            )
                        }
                    ) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filters")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(60.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Column {
                    Box(modifier = Modifier.fillMaxSize()) {
                        EnhancedGoogleMap(
                            beaches = beaches,
                            onMarkerClick = { beach -> selectedBeach.value = beach },
                            mapControls = mapControls.value,
                            onMapControlsChange = { mapControls.value = it }
                        )
                    }
                    BeachDetailsBottomSheet(
                        beach = selectedBeach.value,
                        onDismiss = { selectedBeach.value = null }
                    )
                }
            }
        }
    }
}
