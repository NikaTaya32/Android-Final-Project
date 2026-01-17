package com.example.homeworkstbc.data.mapper

import com.example.homeworkstbc.data.source.local.room.FavoriteRecipeEntity
import com.example.homeworkstbc.domain.model.MyRecipe
import com.example.homeworkstbc.domain.model.RecipeDetails

fun FavoriteRecipeEntity.toDomainMyRecipe() : MyRecipe {
    return MyRecipe(
        id = recipeId,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl,
        instructions = instructions,
        ingredients = ingredients
    )
}