package com.dalvik.newpokedex.domain

import com.dalvik.newpokedex.repository.auth.AuthRepository
import javax.inject.Inject

class FirebaseUserLogoutUseCase @Inject constructor(private val firebaseAuthRepository: AuthRepository) {

     operator fun invoke() {
        return firebaseAuthRepository.logout()
    }
}