package com.singhtwenty2.oceanvista.feature_auth.presentation.onboard_screen

import android.content.Context
import android.os.Build
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.R
import com.singhtwenty2.oceanvista.feature_auth.presentation.onboard_screen.component.OnboardingTexts
import com.singhtwenty2.oceanvista.feature_auth.presentation.onboard_screen.component.TypingTextComposable
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OnBoardingScreenComposable(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onGoogleSignInClick: () -> Unit = {},
    onGithubSignInClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    var showBottomSheet by remember { mutableStateOf(false) }

    val colors = listOf(
        Color(0xFF3A1778),
        Color(0xFFA21FB6),
        Color(0xFF126584),
        Color(0xFF7F0E37)
    )

    var colorIndex by remember { mutableIntStateOf(0) }

    val backgroundColor by animateColorAsState(
        targetValue = colors[colorIndex],
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(backgroundColor, backgroundColor.copy(alpha = 0.9f))))
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TypingTextComposable(
                modifier = Modifier.padding(bottom = 180.dp),
                texts = OnboardingTexts.texts,
                onCharacterTyped = {

                },
                onTextCompleted = {
                    colorIndex = (colorIndex + 1) % colors.size
                }
            )
        }

        AnimatedVisibility(
            visible = showBottomSheet,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 300)
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = onRegisterClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Register", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Login", fontWeight = FontWeight.Bold)
                    }

                    Text(
                        text = "Or",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    OutlinedButton(
                        onClick = onGoogleSignInClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = "Google Sign In",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text("Continue with Google")
                    }

                    OutlinedButton(
                        onClick = onGithubSignInClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.github_logo),
                            contentDescription = "GitHub Sign In",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text("Continue with GitHub")
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(1500)
        showBottomSheet = true
    }
}
