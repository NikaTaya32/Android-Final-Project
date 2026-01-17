package com.example.homeworkstbc.presentation.mapper

import com.example.homeworkstbc.domain.model.Recipe
import com.example.homeworkstbc.presentation.screen.home.model.RecipeModel

fun Recipe.toPresentation() : RecipeModel{
    return RecipeModel(
        id = id,
        title = title,
        publisher = publisher,
        imageUrl = imageUrl,
    )
}