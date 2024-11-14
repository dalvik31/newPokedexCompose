package com.dalvik.newpokedex.ui.common.state

import androidx.annotation.StringRes
import com.dalvik.newpokedex.R

/**
 * Error state holding values for error ui
 */
data class ErrorResourceState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string
)