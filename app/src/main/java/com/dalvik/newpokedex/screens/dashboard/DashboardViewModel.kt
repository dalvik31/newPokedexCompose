package com.dalvik.newpokedex.screens.dashboard

import androidx.lifecycle.ViewModel
import com.dalvik.newpokedex.App
import com.dalvik.newpokedex.domain.FirebaseGetUserUseCase
import com.dalvik.newpokedex.domain.FirebaseUserLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * ViewModel for Login Screen
 */
@HiltViewModel
open class DashboardViewModel @Inject constructor(
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase,
    private val firebaseUserLogoutUseCase: FirebaseUserLogoutUseCase,
    private val app: App
) : ViewModel() {


    var loginState = MutableStateFlow("")
        private set

    init {
        isLogin()
    }


    /**
     * Function to validate a user is Login
     */
    private fun isLogin() {
        val user = firebaseGetUserUseCase.invoke()
        if (user != null) {
            loginState.value = user.email ?: ""
        }
    }

    fun logout(){
        firebaseUserLogoutUseCase.invoke()
    }

}