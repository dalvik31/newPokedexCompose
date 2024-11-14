package com.dalvik.newpokedex.screens.recovery_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.customComposableViews.BodyText
import com.dalvik.newpokedex.ui.common.customComposableViews.SmallClickableWithIconAndText
import com.dalvik.newpokedex.ui.common.customComposableViews.TitleText
import com.dalvik.newpokedex.ui.theme.AppTheme
import com.dalvik.newpokedex.ui.theme.NewPokedexTheme

@Composable
fun RecoveryPasswordScreen(
    recoveryPasswordViewModel: RecoveryPasswordViewModel = hiltViewModel<RecoveryPasswordViewModel>(),
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val recoveryPasswordState by remember {
        recoveryPasswordViewModel.recoveryPasswordState
    }

    if (recoveryPasswordState.isRecoveryPasswordSuccessful) {
        LaunchedEffect(key1 = true) {
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {
        // Full Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {

            // Back Button Icon
            SmallClickableWithIconAndText(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimens.paddingLarge)
                    .padding(top = AppTheme.dimens.paddingLarge),
                iconContentDescription = stringResource(id = R.string.navigate_back),
                iconVector = Icons.AutoMirrored.Outlined.ArrowBack,
                text = stringResource(id = R.string.back_to_login),
                onClick = onNavigateBack
            )


            // Main card Content for Recovery password
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

                    // Heading Recovery password
                    TitleText(
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                        text = stringResource(id = R.string.recovery_password_title)
                    )

                    // Body Registration
                    BodyText(
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                        text = stringResource(id = R.string.recovery_password_body),
                    )

                    /**
                     * Recovery password Inputs Composable
                     */
                    RecoveryPasswordInputs(
                        recoveryPasswordState = recoveryPasswordState,
                        onEmailChange = { inputString ->
                            recoveryPasswordViewModel.onUiEvent(
                                recoveryPasswordUiEvent = RecoveryPasswordUiEvent.EmailChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onSubmit = {
                            recoveryPasswordViewModel.onUiEvent(recoveryPasswordUiEvent = RecoveryPasswordUiEvent.Submit)
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRecoveryPasswordScreen() {
    NewPokedexTheme {
        RecoveryPasswordScreen(onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
    }
}