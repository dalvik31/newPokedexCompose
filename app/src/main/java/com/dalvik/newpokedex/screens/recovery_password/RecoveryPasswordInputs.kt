package com.dalvik.newpokedex.screens.recovery_password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.customComposableViews.EmailTextField
import com.dalvik.newpokedex.ui.common.customComposableViews.NormalButton
import com.dalvik.newpokedex.ui.theme.AppTheme

@Composable
fun RecoveryPasswordInputs(
    recoveryPasswordState: RecoveryPasswordState,
    onEmailOrPhoneChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    // Recovery password Inputs Section
    Column(modifier = Modifier.fillMaxWidth()) {

        // Email or Phone
        EmailTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.dimens.paddingLarge),
            value = recoveryPasswordState.emailOrMobile,
            onValueChange = onEmailOrPhoneChange,
            label = stringResource(id = R.string.recovery_password_email_label),
            isError = recoveryPasswordState.errorState.emailOrMobileErrorState.hasError,
            errorText = stringResource(id = recoveryPasswordState.errorState.emailOrMobileErrorState.errorMessageStringResource),
            imeAction = ImeAction.Next
        )

        // Recovery password Submit Button
        NormalButton(
            modifier = Modifier.padding(top = AppTheme.dimens.paddingExtraLarge),
            text = stringResource(id = R.string.recovery_password_button_text),
            onClick = onSubmit,
        )


    }
}