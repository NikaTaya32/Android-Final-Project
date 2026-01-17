package com.example.homeworkstbc.presentation.screen.myRecipes

sealed interface MyRecipesEvent {
    data object LoadMyRecipes : MyRecipesEvent
}