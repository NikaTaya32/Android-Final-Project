package com.example.homeworkstbc.presentation.screen.favorite

sealed interface FavoriteEvent {
    data object LoadFavorites: FavoriteEvent
}