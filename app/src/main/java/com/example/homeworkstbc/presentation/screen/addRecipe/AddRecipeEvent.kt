package com.example.homeworkstbc.presentation.screen.addRecipe

sealed interface AddRecipeEvent {
    data object AddIngredient : AddRecipeEvent
    data class RemoveIngredient(val position: Int) : AddRecipeEvent
    data class TitleChanged(val text: String) : AddRecipeEvent
    data class InstructionsChanged(val text: String) : AddRecipeEvent
    data class IngredientChanged(val position: Int, val text: String) : AddRecipeEvent
    data class SaveRecipe(val title: String, val instructions: String, val ingredients: List<String>) : AddRecipeEvent
}