package com.dalvik.newpokedex.dependecy_injection

import android.content.Context
import com.dalvik.newpokedex.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    fun providesMainApplicationInstance(@ApplicationContext context: Context): App {
        return context as App
    }

}