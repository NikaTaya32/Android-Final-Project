package com.example.homeworkstbc.presentation.screen.details

sealed class DetailsEvent {
    data class GetRecipeDetails(val recipeId: String) : DetailsEvent()
    data object OnFavoriteClicked : DetailsEvent()
    data class OpenUrlClicked(val url: String) : DetailsEvent()
}