package com.singhtwenty2.oceanvista.feature_auth.presentation.login_sreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.CustomSnackbar
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.EmailTextField
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.LoadingDialog
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.PasswordTextField
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.RegisterButton
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.RegisterScreenText
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.RegisterTopAppBar
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.SnackbarType
import com.singhtwenty2.oceanvista.feature_auth.util.AuthResponseHandler
import kotlinx.coroutines.delay

@Composable
fun LoginScreenComposable(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onBackClick: () -> Unit = {},
    onHomeNavigate: () -> Unit = {}
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    val isEmailFocused by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }

    LaunchedEffect(viewModel, context) {
        viewModel.loginResult.collect { result ->
            showSnackbar = true
            when (result) {
                is AuthResponseHandler.Success -> {
                    snackbarMessage = "Login successful. Redirecting you to home"
                    snackbarType = SnackbarType.SUCCESS
                    delay(1500)
                    onHomeNavigate()
                }

                is AuthResponseHandler.BadRequest -> {
                    snackbarMessage = "Bad Request. Please try again"
                    snackbarType = SnackbarType.ERROR
                }

                is AuthResponseHandler.Conflict -> {
                    snackbarMessage = "Email already exists. Please login"
                    snackbarType = SnackbarType.WARNING
                }

                is AuthResponseHandler.InternalServerError -> {
                    snackbarMessage = "Internal Server Error. Please try again"
                    snackbarType = SnackbarType.ERROR
                }

                is AuthResponseHandler.UnAuthorized -> {
                    snackbarMessage = "Unauthorized. Please try again"
                    snackbarType = SnackbarType.ERROR
                }

                is AuthResponseHandler.UnknownError -> {
                    snackbarMessage = "Unknown Error. Please try again"
                    snackbarType = SnackbarType.ERROR
                }
            }
            delay(1500)
            showSnackbar = false
        }
    }
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    LaunchedEffect(state.email, state.password) {
        isButtonEnabled = state.email.matches(emailRegex) &&
                state.password.isNotEmpty()
    }
    if (state.isLoading) LoadingDialog()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            topBar = {
                RegisterTopAppBar(onBackClick, isEmailFocused, isPasswordFocused = false)
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    EmailTextField(
                        value = state.email,
                        isError = state.email.isNotEmpty() && !state.email.matches(emailRegex),
                        onValueChange = { viewModel.onEvent(LoginUiEvents.EmailChanged(it)) },
                        onNext = { if (isButtonEnabled) viewModel.onEvent(LoginUiEvents.LoginClicked) }
                    )
                    PasswordTextField(
                        value = state.password,
                        isError = state.password.isNotEmpty() && state.password.length < 6,
                        onValueChange = { viewModel.onEvent(LoginUiEvents.PasswordChanged(it)) },
                        onNext = { if (isButtonEnabled) viewModel.onEvent(LoginUiEvents.LoginClicked) }
                    )
                    RegisterButton(
                        buttonText = RegisterScreenText.loginText,
                        isEnabled = isButtonEnabled,
                        onClick = { viewModel.onEvent(LoginUiEvents.LoginClicked) }
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomSnackbar(
            message = snackbarMessage,
            isVisible = showSnackbar,
            type = snackbarType,
            onDismiss = { showSnackbar = false }
        )
    }
}