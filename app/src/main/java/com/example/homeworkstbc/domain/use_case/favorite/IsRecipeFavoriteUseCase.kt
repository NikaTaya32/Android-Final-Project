package com.example.homeworkstbc.domain.use_case.favorite

import com.example.homeworkstbc.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsRecipeFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(recipeId: String, userId: String): Flow<Boolean> {
        return favoritesRepository.isFavorite(recipeId, userId)
    }
}