package com.dalvik.newpokedex.screens.recovery_password

import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.dalvik.newpokedex.extensions.emailFormat
import com.dalvik.newpokedex.extensions.phoneFormat
import com.dalvik.newpokedex.ui.common.state.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for Recovery Password Screen
 */
@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor() : ViewModel() {

    var recoveryPasswordState = mutableStateOf(RecoveryPasswordState())
        private set

    /**
     * Function called on any recovery password event [RecoveryPasswordUiEvent]
     */
    fun onUiEvent(recoveryPasswordUiEvent: RecoveryPasswordUiEvent) {
        when (recoveryPasswordUiEvent) {
            is RecoveryPasswordUiEvent.EmailOrPhoneChanged -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    emailOrMobile = recoveryPasswordUiEvent.inputValue,
                    errorState = recoveryPasswordState.value.errorState.copy(
                        emailOrMobileErrorState = if (recoveryPasswordUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Email or phone empty state
                            emailOrMobileEmptyErrorState
                        } else {
                            // Valid state
                            ErrorState()
                        }

                    )
                )
            }

            RecoveryPasswordUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    recoveryPasswordState.value =
                        recoveryPasswordState.value.copy(isRecoveryPasswordSuccessful = true)
                }
            }
        }
    }

    /**
     * Function to validate inputs
     * Ideally it should be on domain layer (usecase)
     * @return true -> inputs are valid
     * @return false -> inputs are invalid
     */
    private fun validateInputs(): Boolean {
        val emailOrMobileString = recoveryPasswordState.value.emailOrMobile.trim()

        return when {

            // Email/Mobile empty
            emailOrMobileString.isEmpty() -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    errorState = RecoveryPasswordErrorState(
                        emailOrMobileErrorState = emailOrMobileEmptyErrorState
                    )
                )
                false
            }

            // Wrong Mobile format
            (emailOrMobileString.isDigitsOnly() && !emailOrMobileString.phoneFormat()) -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    errorState = RecoveryPasswordErrorState(
                        emailOrMobileErrorState = phoneFormatErrorState
                    )
                )
                false
            }

            // Wrong Email format
            (!emailOrMobileString.isDigitsOnly() && !emailOrMobileString.emailFormat()) -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    errorState = RecoveryPasswordErrorState(
                        emailOrMobileErrorState = emailFormatErrorState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                recoveryPasswordState.value =
                    recoveryPasswordState.value.copy(errorState = RecoveryPasswordErrorState())
                true
            }
        }
    }
}