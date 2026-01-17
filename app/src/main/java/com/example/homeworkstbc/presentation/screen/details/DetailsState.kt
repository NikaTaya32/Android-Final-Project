package com.example.homeworkstbc.presentation.screen.details

import com.example.homeworkstbc.presentation.screen.details.model.RecipeDetailsModel

data class DetailsState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val recipeDetails: RecipeDetailsModel? = null
)
