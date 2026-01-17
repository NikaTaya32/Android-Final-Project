package com.example.homeworkstbc.presentation.screen.myRecipes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyRecipeModel(
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val instructions: String,
    val ingredients: List<String>
) : Parcelable