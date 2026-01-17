package com.example.homeworkstbc.presentation.screen.log_in

sealed class LoginSideEffect {
    data object NavigateToHome : LoginSideEffect()
    data class ShowError(val message: Any) : LoginSideEffect()
}