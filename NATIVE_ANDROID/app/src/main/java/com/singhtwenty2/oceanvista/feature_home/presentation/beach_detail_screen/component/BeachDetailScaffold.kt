@file:OptIn(ExperimentalMaterial3Api::class)

package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.BeachDetailState

@Composable
fun BeachDetailScaffold(
    modifier: Modifier = Modifier,
    state: BeachDetailState,
    onBackPress: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        state = topAppBarState
    )

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            PremiumTopAppBar(
                scrollBehavior = scrollBehavior,
                title = state.beachDetail?.name ?: "Beach Details",
                onBackPress = onBackPress,
                onFavoriteToggle = onFavoriteToggle
            )
        }
    ) { paddingValues ->
        state.beachDetail?.let { beachDetail ->
            BeachDetailContent(
                modifier = Modifier.padding(paddingValues),
                beachDetail = beachDetail
            )
        } ?: run {
            LoadingOrErrorState(
                modifier = Modifier.padding(paddingValues),
                isLoading = state.isLoading
            )
        }
    }
}