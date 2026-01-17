package com.example.homeworkstbc.presentation.screen.home

sealed class HomeSideEffect {
    data object NavigateToAuth: HomeSideEffect()
    data class ShowError(val message: String): HomeSideEffect()
}