package com.dalvik.newpokedex.screens.recovery_password

/**
 * Recovery password Screen Events
 */
sealed class RecoveryPasswordUiEvent {
    data class EmailOrPhoneChanged(val inputValue: String) : RecoveryPasswordUiEvent()
    data object Submit : RecoveryPasswordUiEvent()
}