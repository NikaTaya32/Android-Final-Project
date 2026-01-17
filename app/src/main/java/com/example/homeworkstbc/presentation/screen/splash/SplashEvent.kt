package com.example.homeworkstbc.presentation.screen.splash

sealed interface SplashEvent {
    data object OnStartSplash : SplashEvent
    data object OnStopSplash : SplashEvent
}