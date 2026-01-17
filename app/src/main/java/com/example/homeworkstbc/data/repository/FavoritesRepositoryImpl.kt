package com.example.homeworkstbc.data.repository

import com.example.homeworkstbc.data.mapper.toDomain
import com.example.homeworkstbc.data.mapper.toFavoriteEntity
import com.example.homeworkstbc.data.source.local.room.RecipeDao
import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.domain.model.RecipeDetails
import com.example.homeworkstbc.domain.repository.FavoritesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl @Inject constructor(private val recipeDao: RecipeDao) :
    FavoritesRepository {
    override fun getFavoriteRecipes(userId: String): Flow<List<Recipe>> {
        return recipeDao.getFavoriteRecipes(userId).map {
            it.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun isFavorite(
        recipeId: String,
        userId: String
    ): Flow<Boolean> {
        return recipeDao.isFavorite(recipeId, userId)
    }

    override suspend fun addRecipeToFavorites(
        recipeDetails: RecipeDetails,
        userId: String
    ) {
        recipeDao.addRecipeToFavorites(recipeDetails.toFavoriteEntity(userId))
    }

    override suspend fun removeRecipeFromFavorites(recipeId: String, userId: String) {
        recipeDao.removeRecipeFromFavorites(recipeId, userId)
    }
}