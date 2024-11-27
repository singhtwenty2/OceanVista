package com.singhtwenty2.oceanvista.core

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.singhtwenty2.oceanvista.core.navigation.RootAppNavigationComposable
import com.singhtwenty2.oceanvista.core.ui.theme.OceanVistaTheme
import com.singhtwenty2.oceanvista.core.util.TokenValidator
import com.singhtwenty2.oceanvista.feature_home.domain.service.LocationService

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.FOREGROUND_SERVICE_LOCATION,
                Manifest.permission.POST_NOTIFICATIONS
            ),
            0
        )
        enableEdgeToEdge()
        setContent {
            OceanVistaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                    val token = sharedPreferences.getString("jwt_token", null)
                    val startDestination =
                        if (token != null && !TokenValidator.isTokenExpired(token)) {
                            "home_feature"
                        } else {
                            "auth_feature"
                        }
                    RootAppNavigationComposable(
                        navHostController = navController,
                        startDestination = startDestination,
                        onNavigateToHome = {
                            Intent(applicationContext, LocationService::class.java).apply {
                                action = LocationService.ACTION_START
                                startService(this)
                            }
                        }
                    )
                }
            }
        }
    }
}