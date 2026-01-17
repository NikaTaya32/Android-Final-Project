package com.example.homeworkstbc.presentation.screen.addRecipe

data class AddRecipeState(
    val title: String = "",
    val instructions: String = "",
    val ingredients: List<String> = listOf(""),
    val isLoading: Boolean = false,
)