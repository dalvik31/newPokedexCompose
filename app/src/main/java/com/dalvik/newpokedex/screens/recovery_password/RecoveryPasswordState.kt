package com.dalvik.newpokedex.screens.recovery_password

import com.dalvik.newpokedex.ui.common.state.ErrorState

/**
 * Recovery password State holding ui input values
 */
data class RecoveryPasswordState(
    val emailOrMobile: String = "",
    val errorState: RecoveryPasswordErrorState = RecoveryPasswordErrorState(),
    val isRecoveryPasswordSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class RecoveryPasswordErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
)