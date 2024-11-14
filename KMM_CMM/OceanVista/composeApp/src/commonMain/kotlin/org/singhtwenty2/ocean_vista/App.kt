package org.singhtwenty2.ocean_vista

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.singhtwenty2.ocean_vista.feature_auth.presentation.onboard_screen.OnBoardingScreenComposable

@Composable
@Preview
fun App() {
    MaterialTheme {
        OnBoardingScreenComposable()
    }
}