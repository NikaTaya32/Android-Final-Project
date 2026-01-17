package com.example.homeworkstbc.domain.model

data class RecipeDetails(
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val sourceUrl: String,
    val ingredients: List<String>
)