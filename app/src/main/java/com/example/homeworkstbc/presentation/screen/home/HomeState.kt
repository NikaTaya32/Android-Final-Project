package com.example.homeworkstbc.presentation.screen.home

import com.example.homeworkstbc.presentation.screen.home.model.RecipeModel

data class HomeState(
    val loader: Boolean = false,
    val recipes: List<RecipeModel> = emptyList()
)