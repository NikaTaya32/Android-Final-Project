package com.example.homeworkstbc.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedRecipeDto(
    @SerialName("publisher")
    val publisher: String,
    @SerialName("title")
    val title: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("recipe_id")
    val recipeId: String,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("source_url")
    val sourceUrl: String,
    @SerialName("publisher_url")
    val publisherUrl: String,
)