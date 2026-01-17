package com.example.homeworkstbc.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    suspend fun addRecipeToFavorites(recipe: FavoriteRecipeEntity)

    @Query("DELETE FROM favorite_recipes WHERE recipeId = :recipeId AND userId = :userId")
    suspend fun removeRecipeFromFavorites(recipeId: String, userId: String)

    @Query("SELECT * FROM favorite_recipes WHERE userId = :userId AND is_favorite = 1")
    fun getFavoriteRecipes(userId: String): Flow<List<FavoriteRecipeEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_recipes WHERE recipeId = :recipeId AND userId = :userId LIMIT 1)")
    fun isFavorite(recipeId: String, userId: String): Flow<Boolean>

    @Query("SELECT * FROM favorite_recipes WHERE userId = :userId AND is_own_recipe = 1")
    fun getOwnRecipes(userId: String): Flow<List<FavoriteRecipeEntity>>

    @Query("DELETE FROM favorite_recipes WHERE recipeId = :recipeId AND userId = :userId")
    suspend fun deleteOwnRecipe(recipeId: String, userId: String)
}