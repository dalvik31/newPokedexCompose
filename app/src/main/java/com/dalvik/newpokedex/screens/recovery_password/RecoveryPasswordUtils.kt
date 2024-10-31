package com.dalvik.newpokedex.screens.recovery_password

import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.state.ErrorState


val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email_mobile
)

val emailFormatErrorState = ErrorState(
    hasError = true,
    R.string.login_error_msg_format_email_mobile
)

val phoneFormatErrorState = ErrorState(
    hasError = true,
    R.string.login_error_msg_format_phone_mobile
)