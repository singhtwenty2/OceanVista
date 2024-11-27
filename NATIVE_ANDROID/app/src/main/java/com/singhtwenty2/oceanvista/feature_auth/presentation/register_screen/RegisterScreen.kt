package com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen

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
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.PrefranceDropdownComposable
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.PrivacyPolicyDialog
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.RegisterButton
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.RegisterTopAppBar
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.SnackbarType
import com.singhtwenty2.oceanvista.feature_auth.presentation.register_screen.component.TermsAndPolicySection
import com.singhtwenty2.oceanvista.feature_auth.util.AuthApiResponseHandler
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun RegisterScreenComposable(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    onBackClick: () -> Unit = {},
    onLoginNavigate: () -> Unit = {},
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    val isEmailFocused by remember { mutableStateOf(false) }
    var isTermsAccepted by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    var isPrivacyPolicyDialogVisible by remember { mutableStateOf(false) }

    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    var snackbarType by remember { mutableStateOf(SnackbarType.SUCCESS) }

    val currentDate = remember {
        SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
    } ?: ""

    LaunchedEffect(viewModel, context) {
        viewModel.registerResult.collect { result ->
            showSnackbar = true
            when (result) {
                is AuthApiResponseHandler.Success -> {
                    snackbarMessage = "Registration successful. Please open your email to verify and come back to login."
                    snackbarType = SnackbarType.SUCCESS
                    delay(7000)
                    onLoginNavigate()
                }

                is AuthApiResponseHandler.BadRequest -> {
                    snackbarMessage = "Bad Request. Please try again"
                    snackbarType = SnackbarType.ERROR
                }

                is AuthApiResponseHandler.Conflict -> {
                    snackbarMessage = "Email already exists. Please login"
                    snackbarType = SnackbarType.WARNING
                }

                is AuthApiResponseHandler.InternalServerError -> {
                    snackbarMessage = "Internal Server Error. Please try again"
                    snackbarType = SnackbarType.ERROR
                }

                is AuthApiResponseHandler.UnAuthorized -> {
                    snackbarMessage = "Unauthorized. Please try again"
                    snackbarType = SnackbarType.ERROR
                }

                is AuthApiResponseHandler.UnknownError -> {
                    snackbarMessage = "Unknown Error. Please try again"
                    snackbarType = SnackbarType.ERROR
                }
            }
            delay(7000)
            showSnackbar = false
        }
    }
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    LaunchedEffect(state.email, state.password, state.confirmPassword, isTermsAccepted) {
        isButtonEnabled = state.email.matches(emailRegex) &&
                state.password == state.confirmPassword &&
                state.password.isNotEmpty() &&
                isTermsAccepted
    }

    if (state.isLoading) LoadingDialog()
    if (isPrivacyPolicyDialogVisible) {
        PrivacyPolicyDialog(
            currentDate = currentDate,
            onDismissRequest = { isPrivacyPolicyDialogVisible = false }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            topBar = {
                RegisterTopAppBar(onBackClick, isEmailFocused, isPasswordFocused = false)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    EmailTextField(
                        value = state.email,
                        isError = state.email.isNotEmpty() && !state.email.matches(emailRegex),
                        onValueChange = { viewModel.onEvent(RegisterUiEvents.EmailChanged(it)) },
                        onNext = { if (isButtonEnabled) viewModel.onEvent(RegisterUiEvents.RegisterClicked) }
                    )
                    PasswordTextField(
                        value = state.password,
                        isError = state.password.isNotEmpty() && state.password.length < 6,
                        onValueChange = { viewModel.onEvent(RegisterUiEvents.PasswordChanged(it)) },
                        onNext = { if (isButtonEnabled) viewModel.onEvent(RegisterUiEvents.RegisterClicked) }
                    )

                    PasswordTextField(
                        value = state.confirmPassword,
                        isError = state.confirmPassword.isNotEmpty() &&
                                state.confirmPassword.length < 6 &&
                                state.password != state.confirmPassword,
                        onValueChange = {
                            viewModel.onEvent(
                                RegisterUiEvents.ConfirmPasswordChanged(
                                    it
                                )
                            )
                        },
                        onNext = { if (isButtonEnabled) viewModel.onEvent(RegisterUiEvents.RegisterClicked) }
                    )

                    PrefranceDropdownComposable(
                        selectedItem = state.notificationPrefrence,
                        items = listOf(
                            "Email",
                            "In App",
                            "Push Notification",
                            "All"
                        ),
                        onPrefrenceSelected = {
                            viewModel.onEvent(RegisterUiEvents.NotificationPrefranceChanged(it))
                        }
                    )

                    TermsAndPolicySection(
                        isChecked = isTermsAccepted,
                        onCheckedChange = { isTermsAccepted = it },
                        onPolicyClick = { isPrivacyPolicyDialogVisible = true }
                    )

                    RegisterButton(
                        isEnabled = isButtonEnabled,
                        onClick = { viewModel.onEvent(RegisterUiEvents.RegisterClicked) }
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