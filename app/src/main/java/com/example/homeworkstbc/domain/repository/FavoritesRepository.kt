package com.example.homeworkstbc.domain.repository

import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.domain.model.RecipeDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getFavoriteRecipes(userId: String): Flow<List<Recipe>>

    fun isFavorite(recipeId: String, userId: String): Flow<Boolean>

    suspend fun addRecipeToFavorites(recipeDetails: RecipeDetails, userId: String)

    suspend fun removeRecipeFromFavorites(recipeId: String, userId: String)
}