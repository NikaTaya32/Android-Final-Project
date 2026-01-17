package com.example.homeworkstbc.presentation.screen.myRecipes

import com.example.homeworkstbc.presentation.screen.home.model.RecipeModel
import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel

data class MyRecipesState(
    val myRecipes: List<MyRecipeModel> = emptyList(),
    val loader: Boolean = false
)