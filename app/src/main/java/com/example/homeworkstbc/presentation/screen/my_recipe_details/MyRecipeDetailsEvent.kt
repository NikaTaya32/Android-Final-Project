package com.example.homeworkstbc.presentation.screen.my_recipe_details

import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel

sealed interface MyRecipeDetailsEvent {
    data class LoadMyRecipe(val recipe: MyRecipeModel) : MyRecipeDetailsEvent
    object DeleteMyRecipe : MyRecipeDetailsEvent
}