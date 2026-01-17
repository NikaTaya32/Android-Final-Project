package com.example.homeworkstbc.presentation.screen.details

sealed class DetailsSideEffect {
    data object ShowError : DetailsSideEffect()
    data class OpenUrl(val url: String) : DetailsSideEffect()
}