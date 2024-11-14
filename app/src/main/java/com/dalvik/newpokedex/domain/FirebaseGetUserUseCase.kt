package com.dalvik.newpokedex.domain

import com.dalvik.newpokedex.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseGetUserUseCase @Inject constructor(private val firebaseAuthRepository: AuthRepository) {

    operator fun invoke(): FirebaseUser? {
        return firebaseAuthRepository.getCurrentUser()
    }

}