package com.example.homeworkstbc.domain.use_case.my_recipe

import com.example.homeworkstbc.domain.repository.RecipeRepository
import javax.inject.Inject

class DeleteOwnRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipeId: String) {
        repository.deleteMyRecipe(recipeId)
    }
}