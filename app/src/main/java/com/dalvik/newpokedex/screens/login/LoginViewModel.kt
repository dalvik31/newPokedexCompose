package com.dalvik.newpokedex.screens.login

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalvik.newpokedex.App
import com.dalvik.newpokedex.domain.FirebaseGetUserUseCase
import com.dalvik.newpokedex.domain.FirebaseUserLoginUseCase
import com.dalvik.newpokedex.extensions.emailFormat
import com.dalvik.newpokedex.firebase.Resource
import com.dalvik.newpokedex.ui.common.state.ErrorResourceState
import com.dalvik.newpokedex.ui.common.state.ErrorStringState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Login Screen
 */
@HiltViewModel
open class LoginViewModel @Inject constructor(
    private val firebaseUserLoginUseCase: FirebaseUserLoginUseCase,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase,
    private val app: App
) : ViewModel() {


    var loginState = MutableStateFlow(LoginState())
        private set

    init {
        isLogin()
    }

    /**
     * Function called on any login event [LoginUiEvent]
     */
    fun onUiEvent(loginUiEvent: LoginUiEvent) {
        when (loginUiEvent) {

            // Email changed
            is LoginUiEvent.EmailChanged -> {
                loginState.value = loginState.value.copy(
                    userEmail = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        emailErrorResourceState = if (loginUiEvent.inputValue.trim()
                                .isNotEmpty()
                        )
                            ErrorResourceState()
                        else
                            emailEmptyErrorResourceState
                    )
                )
            }

            // Password changed
            is LoginUiEvent.PasswordChanged -> {
                loginState.value = loginState.value.copy(
                    userPassword = loginUiEvent.inputValue,
                    errorState = loginState.value.errorState.copy(
                        passwordErrorResourceState = if (loginUiEvent.inputValue.trim()
                                .isNotEmpty()
                        )
                            ErrorResourceState()
                        else
                            passwordEmptyErrorResourceState
                    ),
                )
            }

            // Submit Login
            is LoginUiEvent.Submit -> {
                val inputsValidated = validateInputs()
                if (inputsValidated) {
                    doLogin()
                }
            }

            is LoginUiEvent.ErrorShowed -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(serviceErrorState = ErrorStringState())
                )
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
        val emailString = loginState.value.userEmail.trim()
        val passwordString = loginState.value.userPassword
        return when {


            // Email empty
            emailString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailErrorResourceState = emailEmptyErrorResourceState
                    )
                )
                false
            }

            // Wrong Email format
            (!emailString.isDigitsOnly() && !emailString.emailFormat()) -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        emailErrorResourceState = emailFormatErrorResourceState
                    )
                )
                false
            }

            //Password Empty
            passwordString.isEmpty() -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorResourceState = passwordEmptyErrorResourceState
                    )
                )
                false
            }

            passwordString.length < 6 -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        passwordErrorResourceState = passwordFormatErrorResourceState
                    )
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                loginState.value = loginState.value.copy(errorState = LoginErrorState())
                true
            }
        }
    }


    /**
     * Function to validate a user is Login
     */
    private fun isLogin() {
        val user = firebaseGetUserUseCase.invoke()
        if (user != null) {
            loginState.value = loginState.value.copy(isLoginSuccessful = true)
        }
    }


    /**
     * Function to login a user
     */

    private fun doLogin() = viewModelScope.launch {
        val emailString = loginState.value.userEmail.trim()
        val passwordString = loginState.value.userPassword

        val result = firebaseUserLoginUseCase(emailString, passwordString)

        when (result) {
            is Resource.Failure -> {
                loginState.value = loginState.value.copy(
                    errorState = LoginErrorState(
                        serviceErrorState = ErrorStringState(
                            hasError = true,
                            result.exception?.message ?: ""
                        )
                    )
                )
            }

            Resource.Loading -> {}
            is Resource.Success -> {
                loginState.value = loginState.value.copy(isLoginSuccessful = true)
            }
        }


    }

}