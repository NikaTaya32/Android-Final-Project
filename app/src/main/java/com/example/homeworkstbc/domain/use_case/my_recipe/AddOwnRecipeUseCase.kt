package com.example.homeworkstbc.domain.use_case.my_recipe


import com.example.homeworkstbc.domain.repository.RecipeRepository
import javax.inject.Inject

class AddOwnRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {

    suspend operator fun invoke(
        title: String,
        instructions: String,
        ingredients: List<String>
    ) {
        repository.addOwnRecipe(title, instructions, ingredients)
    }
}