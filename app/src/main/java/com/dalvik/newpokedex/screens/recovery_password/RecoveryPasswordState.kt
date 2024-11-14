package com.dalvik.newpokedex.screens.recovery_password

import com.dalvik.newpokedex.ui.common.state.ErrorResourceState

/**
 * Recovery password State holding ui input values
 */
data class RecoveryPasswordState(
    val userEmail: String = "",
    val errorState: RecoveryPasswordErrorState = RecoveryPasswordErrorState(),
    val isRecoveryPasswordSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class RecoveryPasswordErrorState(
    val emailErrorResourceState: ErrorResourceState = ErrorResourceState(),
)