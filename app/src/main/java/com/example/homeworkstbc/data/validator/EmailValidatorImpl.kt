package com.example.homeworkstbc.data.validator

import android.util.Patterns.EMAIL_ADDRESS
import com.example.homeworkstbc.domain.validator.EmailValidator
import jakarta.inject.Inject

class EmailValidatorImpl @Inject constructor() : EmailValidator {
    override fun isValid(email: String): Boolean = EMAIL_ADDRESS.matcher(email).matches()

}