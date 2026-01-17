package com.example.homeworkstbc.presentation.screen.home

sealed class HomeEvent {
    data object LogOut : HomeEvent()
    data class SearchRecipes(val recipe: String) : HomeEvent()
}