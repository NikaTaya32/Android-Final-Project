package com.example.homeworkstbc.data.mapper

import com.example.homeworkstbc.data.source.remote.model.RecipeDto
import com.example.homeworkstbc.domain.model.Recipe


fun RecipeDto.toDomain(): Recipe {
    return Recipe(
        id = recipeId,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl,
    )
}