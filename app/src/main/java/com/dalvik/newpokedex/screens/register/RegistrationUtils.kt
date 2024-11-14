package com.dalvik.newpokedex.screens.register

import com.dalvik.newpokedex.R
import com.dalvik.newpokedex.ui.common.state.ErrorResourceState


val emailEmptyErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.registration_error_msg_empty_email
)

val passwordEmptyErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.registration_error_msg_empty_password
)

val confirmPasswordEmptyErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.registration_error_msg_empty_confirm_password
)

val passwordMismatchErrorResourceState = ErrorResourceState(
    hasError = true,
    errorMessageStringResource = R.string.registration_error_msg_password_mismatch
)