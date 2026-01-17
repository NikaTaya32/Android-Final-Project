package com.example.homeworkstbc.data.repository

import com.example.homeworkstbc.data.common.HandleResponse
import com.example.homeworkstbc.data.common.asResource
import com.example.homeworkstbc.data.mapper.toDomain
import com.example.homeworkstbc.data.mapper.toDomainMyRecipe
import com.example.homeworkstbc.data.source.local.room.RecipeDao
import com.example.homeworkstbc.data.source.local.room.FavoriteRecipeEntity
import com.example.homeworkstbc.data.source.remote.api_service.DetailsApiService
import com.example.homeworkstbc.data.source.remote.api_service.RecipeApiService
import com.example.homeworkstbc.domain.model.MyRecipe
import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.domain.model.RecipeDetails
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.repository.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApiService: RecipeApiService,
    private val handleResponse: HandleResponse,
    private val detailsApiService: DetailsApiService,
    private val recipeDao: RecipeDao,
    private val firebaseAuth: FirebaseAuth,
) : RecipeRepository {

    override fun searchRecipes(query: String): Flow<Resource<List<Recipe>>> {
        return handleResponse.safeApiCall {
            recipeApiService.searchRecipes(query)
        }.asResource { recipeResponse ->
            recipeResponse.recipes.map {
                it.toDomain()
            }
        }
    }

    override fun getRecipeDetails(id: String): Flow<Resource<RecipeDetails>> {
        return handleResponse.safeApiCall {
            detailsApiService.getRecipeDetails(id)
        }.asResource { recipe ->
            recipe.recipe.toDomain()
        }
    }

    override suspend fun addOwnRecipe(
        title: String,
        instructions: String,
        ingredients: List<String>,
    ) {
        val currentUserId = firebaseAuth.currentUser?.uid ?: ""
        val newRecipeEntity = FavoriteRecipeEntity(
            recipeId = UUID.randomUUID().toString(),
            title = title,
            instructions = instructions,
            ingredients = ingredients,
            isOwnRecipe = true,
            isFavorite = false,
            imageUrl = "",
            publisher = "",
            sourceUrl = "",
            userId = currentUserId
        )

        recipeDao.addRecipeToFavorites(newRecipeEntity)
    }

    override fun getOwnRecipes(): Flow<List<MyRecipe>> {
        val currentUserId = firebaseAuth.currentUser?.uid ?: ""

        return recipeDao.getOwnRecipes(currentUserId).map { entities ->
            entities.map { entity ->
                entity.toDomainMyRecipe()
            }
        }
    }

    override suspend fun deleteMyRecipe(recipeId: String) {
        val userId = firebaseAuth.currentUser?.uid ?: ""
        recipeDao.deleteOwnRecipe(recipeId = recipeId, userId = userId)
    }
}