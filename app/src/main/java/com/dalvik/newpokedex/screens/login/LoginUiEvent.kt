package com.dalvik.newpokedex.screens.login

/**
 * Login Screen Events
 */
sealed class LoginUiEvent {
    data class EmailChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    object ErrorShowed : LoginUiEvent()
    object Submit : LoginUiEvent()
}