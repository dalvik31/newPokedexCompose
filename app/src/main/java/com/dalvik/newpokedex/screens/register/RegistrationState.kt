package com.dalvik.newpokedex.screens.register

import com.dalvik.newpokedex.ui.common.state.ErrorResourceState
import com.dalvik.newpokedex.ui.common.state.ErrorStringState

/**
 * Registration State holding ui input values
 */
data class RegistrationState(
    val userEmail: String = "",
    val userPassword: String = "",
    val confirmPassword: String = "",
    val errorState: RegistrationErrorState = RegistrationErrorState(),
    val isRegistrationSuccessful: Boolean = false
)

/**
 * Error state in registration holding respective
 * text field validation errors
 */
data class RegistrationErrorState(
    val emailIdErrorResourceState: ErrorResourceState = ErrorResourceState(),
    val passwordErrorResourceState: ErrorResourceState = ErrorResourceState(),
    val confirmPasswordErrorResourceState: ErrorResourceState = ErrorResourceState(),
    val serviceErrorState: ErrorStringState = ErrorStringState(),
)