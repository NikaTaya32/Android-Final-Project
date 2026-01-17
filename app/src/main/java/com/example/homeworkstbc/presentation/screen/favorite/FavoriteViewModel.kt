package com.example.homeworkstbc.presentation.screen.favorite

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.use_case.auth.GetCurrentUserIdUseCase
import com.example.homeworkstbc.domain.use_case.favorite.GetFavoriteRecipesUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import com.example.homeworkstbc.presentation.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteRecipesUseCase: GetFavoriteRecipesUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase
) : BaseViewModel<FavoriteState, FavoriteSideEffect>(FavoriteState()) {

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.LoadFavorites -> loadFavorites()
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            val userId = getCurrentUserIdUseCase() ?: return@launch
            getFavoriteRecipesUseCase.invoke(userId).collect { recipeLst ->
                updateState { state -> state.copy(recipes = recipeLst.map { it.toPresentation() }) }
            }
        }
    }

}