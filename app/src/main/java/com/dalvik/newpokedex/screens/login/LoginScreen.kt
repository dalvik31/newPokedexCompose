package com.dalvik.newpokedex.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.customComposableViews.TitleText
import com.dalvik.newpokedex.ui.common.customComposableViews.snack_bar.SnackBarError
import com.dalvik.newpokedex.ui.common.customComposableViews.snack_bar.rememberSnackBarState
import com.dalvik.newpokedex.ui.theme.AppTheme
import com.dalvik.newpokedex.ui.theme.NewPokedexTheme


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val loginState = loginViewModel.loginState.collectAsState()

    if (loginState.value.isLoginSuccessful) {
        /**
         * Navigate to Authenticated navigation route
         * once login is successful
         */
        LaunchedEffect(key1 = true) {
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {

        ScreenContent(
            loginState = loginState.value,
            onNavigateToRegistration,
            onNavigateToForgotPassword,
            onEmailChange = { inputString ->
                loginViewModel.onUiEvent(
                    loginUiEvent = LoginUiEvent.EmailChanged(
                        inputString
                    )
                )
            },
            onPasswordChange = { inputString ->
                loginViewModel.onUiEvent(
                    loginUiEvent = LoginUiEvent.PasswordChanged(
                        inputString
                    )
                )
            },
            onSubmit = {
                loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
            },
            onShowedError = { loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.ErrorShowed) })
    }
}

@Composable
fun ScreenContent(
    loginState: LoginState,
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onShowedError: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .systemBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        val snackBarError = rememberSnackBarState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.paddingLarge)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.dimens.paddingLarge)
                        .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                ) {
                    // Login Logo
                    AsyncImage(
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(128.dp)
                            .padding(top = AppTheme.dimens.paddingSmall),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data = R.drawable.ic_logo)
                            .crossfade(enable = true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = stringResource(id = R.string.login_heading_text)
                    )

                    // Heading Login
                    TitleText(
                        modifier = Modifier
                            .padding(top = AppTheme.dimens.paddingLarge)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.login_heading_text),
                    )

                    // Login Inputs Composable
                    LoginInputs(
                        loginState = loginState,
                        onEmailChange = onEmailChange,
                        onPasswordChange = onPasswordChange,
                        onSubmit = onSubmit,
                        onForgotPasswordClick = onNavigateToForgotPassword
                    )

                    if (loginState.errorState.serviceErrorState.hasError) {
                        snackBarError.addMessage(loginState.errorState.serviceErrorState.errorMessageStringResource)
                        onShowedError.invoke()
                    }

                }
            }

            Row(
                modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Don't have an account?
                Text(text = stringResource(id = R.string.do_not_have_account))

                //Register
                Text(
                    modifier = Modifier
                        .padding(start = AppTheme.dimens.paddingExtraSmall)
                        .clickable {
                            onNavigateToRegistration.invoke()
                        },
                    text = stringResource(id = R.string.register),
                    color = MaterialTheme.colorScheme.primary
                )
            }


        }
        SnackBarError(state = snackBarError)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    NewPokedexTheme {
        ScreenContent(
            loginState = LoginState(),
            onNavigateToForgotPassword = {},
            onNavigateToRegistration = {},
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {},
            onShowedError = {}
        )
    }
}