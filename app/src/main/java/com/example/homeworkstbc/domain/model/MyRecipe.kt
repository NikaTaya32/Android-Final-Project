package com.example.homeworkstbc.domain.model

data class MyRecipe(
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val instructions: String,
    val ingredients: List<String>
)