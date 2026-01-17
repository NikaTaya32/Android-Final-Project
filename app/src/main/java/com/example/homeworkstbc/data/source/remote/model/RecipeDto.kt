package com.example.homeworkstbc.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("publisher")
    val publisher: String,
    @SerialName("title")
    val title: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("recipe_id")
    val recipeId: String
)