package com.singhtwenty2.oceanvista.core.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen.LoginScreenComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen.LoginViewModel
import com.singhtwenty2.oceanvista.feature_auth.presentation.onboard_screen.OnBoardingScreenComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.RegisterScreenComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.RegisterViewModel
import com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.HomeScreenComposable
import com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.HomeViewModel
import org.koin.androidx.compose.getViewModel

@SuppressLint("NewApi")
@Composable
fun RootAppNavigationComposable(
    navHostController: NavHostController,
    startDestination: String = "auth_feature",
    onNavigateToHome: () -> Unit
) {
    val registerViewModel = getViewModel<RegisterViewModel>()
    val loginViewModel = getViewModel<LoginViewModel>()
    val homeViewModel = getViewModel<HomeViewModel>()

    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        enterTransition = {
            fadeIn(animationSpec = tween(550)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(550)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(550)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(550)
            )
        },
    ) {
        // Auth Feature Screen
        navigation(
            startDestination = "onboard_screen",
            route = "auth_feature"
        ) {
            composable("onboard_screen") {
                OnBoardingScreenComposable(
                    onRegisterClick = {
                        navHostController.navigate("register_screen")
                    },
                    onLoginClick = {
                        navHostController.navigate("login_screen")
                    }
                )
            }
            composable("register_screen") {
                RegisterScreenComposable(
                    viewModel = registerViewModel,
                    onLoginNavigate = {
                        navHostController.navigate("login_screen")
                    },
                    onBackClick = {
                        navHostController.navigateUp()
                    }
                )
            }
            composable("login_screen") {
                LoginScreenComposable(
                    viewModel = loginViewModel,
                    onBackClick = {
                        navHostController.navigateUp()
                    },
                    onHomeNavigate = {
                        navHostController.navigate("home_screen") {
                            popUpTo("auth_feature") {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
        navigation(
            startDestination = "home_screen",
            route = "home_feature"
        ) {
            composable(
                "home_screen"
            ) {
                HomeScreenComposable(
                    viewModel = homeViewModel
                )
                LaunchedEffect(Unit) {
                    onNavigateToHome()
                }
            }
        }
    }
}