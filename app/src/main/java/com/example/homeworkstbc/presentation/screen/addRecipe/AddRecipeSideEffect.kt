package com.example.homeworkstbc.presentation.screen.addRecipe

sealed interface AddRecipeSideEffect {
    data object SaveSuccess : AddRecipeSideEffect
    data object ShowError : AddRecipeSideEffect
}