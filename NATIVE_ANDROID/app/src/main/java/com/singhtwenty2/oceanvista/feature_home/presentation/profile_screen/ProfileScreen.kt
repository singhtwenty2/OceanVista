package com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.singhtwenty2.oceanvista.feature_home.presentation.profile_screen.component.ProfileScreenContent

@Composable
fun ProfileScreenComposable(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onLogoutClicked: () -> Unit,
    viewModel: ProfileViewModel
) {
    val state = viewModel.state.value

    ProfileScreenContent(
        modifier = modifier,
        profile = state,
        onBackPressed = onBackPressed,
        onLogoutClicked = onLogoutClicked
    )
}