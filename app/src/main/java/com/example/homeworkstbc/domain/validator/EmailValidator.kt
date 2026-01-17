package com.example.homeworkstbc.domain.validator

interface EmailValidator {
    fun isValid(email: String) : Boolean
}