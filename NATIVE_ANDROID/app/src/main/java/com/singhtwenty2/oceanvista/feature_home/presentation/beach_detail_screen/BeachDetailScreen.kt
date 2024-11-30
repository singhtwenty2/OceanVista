package com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.component.BeachDetailScaffold

@Composable
fun BeachDetailScreenComposable(
    modifier: Modifier = Modifier,
    viewModel: BeachDetailViewModel,
    onBackPress: () -> Unit,
    onFavoriteToggle: () -> Unit = {},
    beachId: Long
) {
    val state = viewModel.state.value

    BeachDetailScaffold(
        modifier = modifier,
        state = state,
        onBackPress = onBackPress,
        onFavoriteToggle = onFavoriteToggle
    )
}