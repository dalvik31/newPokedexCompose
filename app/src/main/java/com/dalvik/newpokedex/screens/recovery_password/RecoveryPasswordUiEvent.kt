package com.dalvik.newpokedex.screens.recovery_password

/**
 * Recovery password Screen Events
 */
sealed class RecoveryPasswordUiEvent {
    data class EmailChanged(val inputValue: String) : RecoveryPasswordUiEvent()
    data object Submit : RecoveryPasswordUiEvent()
}