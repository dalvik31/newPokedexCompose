package com.dalvik.newpokedex.screens.login

import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.state.ErrorState

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email_mobile
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)

val passwordFormatErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_format_password
)
val emailFormatErrorState = ErrorState(
    hasError = true,
    R.string.login_error_msg_format_email_mobile
)

val phoneFormatErrorState = ErrorState(
    hasError = true,
    R.string.login_error_msg_format_phone_mobile
)