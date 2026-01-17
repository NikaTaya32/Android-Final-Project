package com.example.homeworkstbc.domain.use_case.favorite

import com.example.homeworkstbc.domain.repository.FavoritesRepository
import javax.inject.Inject

class RemoveRecipeFromFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(recipeId: String, userId: String) {
        favoritesRepository.removeRecipeFromFavorites(recipeId, userId)
    }
}