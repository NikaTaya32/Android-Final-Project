package com.example.homeworkstbc.domain.use_case.recipe

import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Recipe>>> {
        return repository.searchRecipes(query)
    }
}