package com.example.homeworkstbc.data.mapper

import com.example.homeworkstbc.data.source.local.room.FavoriteRecipeEntity
import com.example.homeworkstbc.domain.model.RecipeDetails

fun RecipeDetails.toFavoriteEntity(userId: String): FavoriteRecipeEntity {
    return FavoriteRecipeEntity(
        recipeId = id,
        title = title,
        imageUrl = imageUrl,
        publisher = publisher,
        ingredients = ingredients,
        sourceUrl = sourceUrl,
        userId = userId,
        isFavorite = true,
        isOwnRecipe = false
    )
}