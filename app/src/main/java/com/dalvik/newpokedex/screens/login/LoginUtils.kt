package com.dalvik.newpokedex.screens.login

import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.state.ErrorResourceState

val emailEmptyErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email
)

val passwordEmptyErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)

val passwordFormatErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_format_password
)
val emailFormatErrorResourceState = ErrorResourceState(
    hasError = true,
    R.string.login_error_msg_format_email
)