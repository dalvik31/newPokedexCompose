package com.dalvik.newpokedex.screens.recovery_password

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dalvik.newpokedex.extensions.emailFormat
import com.dalvik.newpokedex.ui.common.state.ErrorResourceState
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
            is RecoveryPasswordUiEvent.EmailChanged -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    userEmail = recoveryPasswordUiEvent.inputValue,
                    errorState = recoveryPasswordState.value.errorState.copy(
                        emailErrorResourceState = if (recoveryPasswordUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Email or phone empty state
                            emailEmptyErrorResourceState
                        } else {
                            // Valid state
                            ErrorResourceState()
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
        val emailString = recoveryPasswordState.value.userEmail.trim()

        return when {

            // Email empty
            emailString.isEmpty() -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    errorState = RecoveryPasswordErrorState(
                        emailErrorResourceState = emailEmptyErrorResourceState
                    )
                )
                false
            }

            // Wrong Email format
            (!emailString.emailFormat()) -> {
                recoveryPasswordState.value = recoveryPasswordState.value.copy(
                    errorState = RecoveryPasswordErrorState(
                        emailErrorResourceState = emailFormatErrorResourceState
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