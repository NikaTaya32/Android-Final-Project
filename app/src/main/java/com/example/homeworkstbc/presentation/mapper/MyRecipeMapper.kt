package com.example.homeworkstbc.presentation.mapper

import com.example.homeworkstbc.domain.model.MyRecipe
import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel
fun MyRecipe.toPresentation(): MyRecipeModel {
    return MyRecipeModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        publisher = publisher,
        instructions = instructions,
        ingredients = ingredients
    )
}

