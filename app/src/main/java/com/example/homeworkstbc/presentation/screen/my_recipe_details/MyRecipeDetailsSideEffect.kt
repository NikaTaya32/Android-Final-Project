package com.example.homeworkstbc.presentation.screen.my_recipe_details

sealed interface MyRecipeDetailsSideEffect {
    object ShowError : MyRecipeDetailsSideEffect
    object NavigateBack : MyRecipeDetailsSideEffect
}
