package com.dalvik.newpokedex.domain

import com.dalvik.newpokedex.firebase.Resource
import com.dalvik.newpokedex.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseUserLoginUseCase @Inject constructor(private val firebaseAuthRepository: AuthRepository) {
    suspend operator fun invoke(userEmail: String, userPassword: String): Resource<FirebaseUser> {
        return firebaseAuthRepository.login(userEmail, userPassword)
    }
}