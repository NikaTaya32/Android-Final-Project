package com.example.homeworkstbc.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleRecipeResponseDto(
    @SerialName("recipe")
    val recipe: DetailedRecipeDto
)