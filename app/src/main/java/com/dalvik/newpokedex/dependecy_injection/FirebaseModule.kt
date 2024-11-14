package com.dalvik.newpokedex.dependecy_injection

import com.dalvik.newpokedex.repository.auth.AuthRepository
import com.epacheco.reports.compose_reformat.repository.auth.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    internal fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFirebaseAuthRepository(impl: FirebaseAuthRepository): AuthRepository = impl

}