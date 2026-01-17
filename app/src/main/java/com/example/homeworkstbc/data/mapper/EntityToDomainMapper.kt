package com.example.homeworkstbc.data.mapper

import com.example.homeworkstbc.data.source.local.room.FavoriteRecipeEntity
import com.example.homeworkstbc.domain.model.Recipe

fun FavoriteRecipeEntity.toDomain() : Recipe {
    return Recipe(
        id = recipeId,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl,
    )
}