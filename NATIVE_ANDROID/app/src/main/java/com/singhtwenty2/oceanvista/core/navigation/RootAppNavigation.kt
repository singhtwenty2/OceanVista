package com.singhtwenty2.oceanvista.core.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen.LoginScreenComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen.LoginViewModel
import com.singhtwenty2.oceanvista.feature_auth.presentation.onboard_screen.OnBoardingScreenComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.RegisterScreenComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.RegisterViewModel
import com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.BeachDetailScreenComposable
import com.singhtwenty2.oceanvista.feature_home.presentation.beach_detail_screen.BeachDetailViewModel
import com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.HomeScreenComposable
import com.singhtwenty2.oceanvista.feature_home.presentation.home_screen.HomeViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

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
                    viewModel = homeViewModel,
                    onBeachClick = {
                        navHostController.navigate("beach_detail_screen/$it")
                        Log.d("RootAppNavigation", "BeachId: $it")
                    }
                )
                LaunchedEffect(Unit) {
                    onNavigateToHome()
                }
            }
            composable(
                route = "beach_detail_screen/{beachId}",
                arguments = listOf(
                    navArgument("beachId") {
                        type = NavType.LongType
                    }
                )
            ) { backStackEntry ->
                val beachId = backStackEntry.arguments?.getLong("beachId")
                    ?: throw IllegalArgumentException("Invalid beachId")
                val beachDetailViewModel: BeachDetailViewModel =
                    getViewModel(parameters = { parametersOf(beachId) })
                BeachDetailScreenComposable(
                    viewModel = beachDetailViewModel,
                    onBackPress = {
                        navHostController.popBackStack()
                    },
                    beachId = beachId
                )
            }
        }
    }
}