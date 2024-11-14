package com.dalvik.newpokedex.ui.common.state

/**
 * Error state holding values for error ui
 */
data class ErrorStringState(
    val hasError: Boolean = false,
    val errorMessageStringResource: String = ""
)