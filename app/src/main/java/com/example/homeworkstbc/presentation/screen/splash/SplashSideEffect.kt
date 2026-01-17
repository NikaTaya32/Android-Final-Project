package com.example.homeworkstbc.presentation.screen.splash

sealed interface SplashSideEffect {
    data object NavigateToAuth : SplashSideEffect
    data object NavigateToHome : SplashSideEffect
}