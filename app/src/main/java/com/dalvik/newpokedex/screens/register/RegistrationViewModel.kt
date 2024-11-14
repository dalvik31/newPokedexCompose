package com.dalvik.newpokedex.screens.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalvik.newpokedex.domain.FirebaseUserSignUpUseCase
import com.dalvik.newpokedex.firebase.Resource
import com.dalvik.newpokedex.ui.common.state.ErrorResourceState
import com.dalvik.newpokedex.ui.common.state.ErrorStringState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Register Screen
 */
@HiltViewModel
class RegistrationViewModel @Inject constructor(private val firebaseUserSignUpUseCase: FirebaseUserSignUpUseCase) :
    ViewModel() {

    var registrationState = mutableStateOf(RegistrationState())
        private set

    /**
     * Function called on any login event [RegistrationUiEvent]
     */
    fun onUiEvent(registrationUiEvent: RegistrationUiEvent) {
        when (registrationUiEvent) {

            // Email id changed event
            is RegistrationUiEvent.EmailChanged -> {
                registrationState.value = registrationState.value.copy(
                    userEmail = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        emailIdErrorResourceState = if (registrationUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Email id empty state
                            emailEmptyErrorResourceState
                        } else {
                            // Valid state
                            ErrorResourceState()
                        }

                    )
                )
            }

            // Password changed event
            is RegistrationUiEvent.PasswordChanged -> {
                registrationState.value = registrationState.value.copy(
                    userPassword = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        passwordErrorResourceState = if (registrationUiEvent.inputValue.trim()
                                .isEmpty()
                        ) {
                            // Password Empty state
                            passwordEmptyErrorResourceState
                        } else {
                            // Valid state
                            ErrorResourceState()
                        }

                    )
                )
            }

            // Confirm Password changed event
            is RegistrationUiEvent.ConfirmPasswordChanged -> {
                registrationState.value = registrationState.value.copy(
                    confirmPassword = registrationUiEvent.inputValue,
                    errorState = registrationState.value.errorState.copy(
                        confirmPasswordErrorResourceState = when {

                            // Empty state of confirm password
                            registrationUiEvent.inputValue.trim().isEmpty() -> {
                                confirmPasswordEmptyErrorResourceState
                            }

                            // Password is different than the confirm password
                            registrationState.value.userPassword.trim() != registrationUiEvent.inputValue -> {
                                passwordMismatchErrorResourceState
                            }

                            // Valid state
                            else -> ErrorResourceState()
                        }
                    )
                )
            }


            // Submit Registration event
            is RegistrationUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    registerUser()
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
        val emailString = registrationState.value.userEmail.trim()
        val passwordString = registrationState.value.userPassword.trim()
        val confirmPasswordString = registrationState.value.confirmPassword.trim()

        return when {

            // Email empty
            emailString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        emailIdErrorResourceState = emailEmptyErrorResourceState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        passwordErrorResourceState = passwordEmptyErrorResourceState
                    )
                )
                false
            }

            //Confirm Password Empty
            confirmPasswordString.isEmpty() -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        confirmPasswordErrorResourceState = confirmPasswordEmptyErrorResourceState
                    )
                )
                false
            }

            // Password and Confirm Password are different
            passwordString != confirmPasswordString -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        confirmPasswordErrorResourceState = passwordMismatchErrorResourceState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                registrationState.value =
                    registrationState.value.copy(errorState = RegistrationErrorState())
                true
            }
        }
    }


    /**
     * Function to login a user
     */

    private fun registerUser() = viewModelScope.launch {
        val emailString = registrationState.value.userEmail.trim()
        val passwordString = registrationState.value.userPassword

        val result = firebaseUserSignUpUseCase(emailString, passwordString)

        when (result) {
            is Resource.Failure -> {
                registrationState.value = registrationState.value.copy(
                    errorState = RegistrationErrorState(
                        serviceErrorState = ErrorStringState(
                            hasError = true,
                            result.exception?.message ?: ""
                        )
                    )
                )
            }

            Resource.Loading -> {

            }

            is Resource.Success -> {
                registrationState.value =
                    registrationState.value.copy(isRegistrationSuccessful = true)
            }
        }


    }
}