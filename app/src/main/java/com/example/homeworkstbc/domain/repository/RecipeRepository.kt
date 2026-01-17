package com.example.homeworkstbc.domain.repository

import com.example.homeworkstbc.domain.model.MyRecipe
import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.domain.model.RecipeDetails
import com.example.homeworkstbc.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun searchRecipes(query: String): Flow<Resource<List<Recipe>>>

    fun getRecipeDetails(id: String): Flow<Resource<RecipeDetails>>
    suspend fun addOwnRecipe(title: String, instructions: String, ingredients: List<String>)
    fun getOwnRecipes(): Flow<List<MyRecipe>>
    suspend fun deleteMyRecipe(recipeId: String)
}