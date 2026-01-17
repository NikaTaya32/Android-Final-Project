package com.example.homeworkstbc.domain.use_case.my_recipe

import jakarta.inject.Inject

class ValidateRecipeInputUseCase @Inject constructor() {

    operator fun invoke(title: String, ingredients: List<String>): Boolean {
        if (title.isBlank() || ingredients.all { it.isBlank() }) {
            return false
        }

        return true
    }
}