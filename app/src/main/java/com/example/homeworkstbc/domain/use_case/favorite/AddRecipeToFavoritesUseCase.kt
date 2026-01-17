package com.example.homeworkstbc.domain.use_case.favorite

import com.example.homeworkstbc.domain.model.RecipeDetails
import com.example.homeworkstbc.domain.repository.FavoritesRepository
import javax.inject.Inject

class AddRecipeToFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(recipeDetails: RecipeDetails, userId: String) {
        favoritesRepository.addRecipeToFavorites(recipeDetails, userId)
    }
}