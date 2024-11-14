package com.dalvik.newpokedex.extensions

import androidx.core.text.isDigitsOnly

fun String.emailFormat(): Boolean {
    return (android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun String.getNameFromEmail(): String = this.substringBefore("@")