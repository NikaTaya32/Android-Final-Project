package com.example.homeworkstbc.presentation.mapper

import com.example.homeworkstbc.domain.model.RecipeDetails
import com.example.homeworkstbc.presentation.screen.details.model.RecipeDetailsModel

fun RecipeDetailsModel.toDomain(): RecipeDetails {
    return RecipeDetails(
        id = id,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl,
        sourceUrl = sourceUrl,
        ingredients = ingredients
    )
}