package com.example.homeworkstbc.data.mapper

import com.example.homeworkstbc.data.source.remote.model.DetailedRecipeDto
import com.example.homeworkstbc.domain.model.RecipeDetails

fun DetailedRecipeDto.toDomain(): RecipeDetails {
    return RecipeDetails(
        id = this.recipeId,
        title = this.title,
        publisher = this.publisher,
        imageUrl = this.imageUrl,
        sourceUrl = this.sourceUrl,
        ingredients = this.ingredients
    )
}