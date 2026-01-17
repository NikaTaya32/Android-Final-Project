package com.example.homeworkstbc.domain.use_case.favorite

import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteRecipesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(userId: String): Flow<List<Recipe>> {
        return favoritesRepository.getFavoriteRecipes(userId)
    }
}