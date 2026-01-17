package com.example.homeworkstbc.presentation.screen.favorite

import com.example.homeworkstbc.presentation.screen.home.model.RecipeModel

data class FavoriteState(
    val recipes: List<RecipeModel> = emptyList()
)