package com.example.homeworkstbc.domain.use_case.recipe

import com.example.homeworkstbc.domain.model.RecipeDetails
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(recipeId: String): Flow<Resource<RecipeDetails>> {
        return repository.getRecipeDetails(recipeId)
    }
}