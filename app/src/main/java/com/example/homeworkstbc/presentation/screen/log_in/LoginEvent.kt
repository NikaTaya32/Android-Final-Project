package com.example.homeworkstbc.presentation.screen.log_in

sealed class LoginEvent {
    data class LoginUser(val email: String, val password: String) : LoginEvent()
}