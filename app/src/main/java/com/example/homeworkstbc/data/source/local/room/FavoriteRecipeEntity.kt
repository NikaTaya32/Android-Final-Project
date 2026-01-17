package com.example.homeworkstbc.data.source.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
class FavoriteRecipeEntity (
    @PrimaryKey
    val recipeId: String,
    val title: String,
    val imageUrl: String,
    val publisher: String,
    val ingredients: List<String>,
    val sourceUrl: String,
    val userId: String,
    @ColumnInfo(name = "instructions", defaultValue = "")
    val instructions: String = "",
    @ColumnInfo(name = "is_favorite", defaultValue = "0")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "is_own_recipe", defaultValue = "0")
    val isOwnRecipe: Boolean = false
)