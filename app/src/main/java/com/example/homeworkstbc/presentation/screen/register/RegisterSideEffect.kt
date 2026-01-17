package com.example.homeworkstbc.presentation.screen.register

sealed class RegisterSideEffect {
    data object NavigateToHome : RegisterSideEffect()
    data class SendError(val message: Any) : RegisterSideEffect()
}
