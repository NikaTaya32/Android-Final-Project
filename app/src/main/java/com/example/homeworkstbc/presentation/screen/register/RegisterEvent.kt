package com.example.homeworkstbc.presentation.screen.register

sealed class RegisterEvent {
    data class RegisterUser(val email: String, val password: String, val repeatPassword: String) :
        RegisterEvent()

}