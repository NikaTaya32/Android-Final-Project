package com.example.homeworkstbc.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseDto(
    @SerialName("recipes")
    val recipes: List<RecipeDto>
)