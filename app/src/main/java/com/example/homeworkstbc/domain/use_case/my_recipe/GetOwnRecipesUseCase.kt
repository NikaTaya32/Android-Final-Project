package com.example.homeworkstbc.domain.use_case.my_recipe

import com.example.homeworkstbc.domain.model.MyRecipe
import com.example.homeworkstbc.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOwnRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<List<MyRecipe>> {
        return repository.getOwnRecipes()
    }
}