package com.dalvik.newpokedex.screens.login

import com.dalvik.newpokedex.ui.common.state.ErrorResourceState
import com.dalvik.newpokedex.ui.common.state.ErrorStringState


/**
 * Login State holding ui input values
 */
data class LoginState(
    val userEmail: String = "",
    val userPassword: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isLoginSuccessful: Boolean = false
)

/**
 *
 * Error state in login holding respective
 * text field validation errors
 */
data class LoginErrorState(
    val emailErrorResourceState: ErrorResourceState = ErrorResourceState(),
    val passwordErrorResourceState: ErrorResourceState = ErrorResourceState(),
    val serviceErrorState: ErrorStringState = ErrorStringState(),
)