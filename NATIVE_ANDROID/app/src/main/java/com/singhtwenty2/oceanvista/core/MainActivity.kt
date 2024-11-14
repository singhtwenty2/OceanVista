package com.singhtwenty2.oceanvista.core

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.singhtwenty2.oceanvista.core.navigation.RootAppNavigationComposable
import com.singhtwenty2.oceanvista.core.ui.theme.OceanVistaTheme
import com.singhtwenty2.oceanvista.core.util.TokenValidator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OceanVistaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                    val token = sharedPreferences.getString("jwt_token", null)
                    val startDestination = if (token != null && !TokenValidator.isTokenExpired(token)) {
                        "home_feature"
                    } else {
                        "auth_feature"
                    }
                    RootAppNavigationComposable(
                        navHostController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}