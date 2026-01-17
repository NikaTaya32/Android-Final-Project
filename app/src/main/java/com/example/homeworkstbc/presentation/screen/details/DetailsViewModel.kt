package com.example.homeworkstbc.presentation.screen.details

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.use_case.auth.GetCurrentUserIdUseCase
import com.example.homeworkstbc.domain.use_case.favorite.AddRecipeToFavoritesUseCase
import com.example.homeworkstbc.domain.use_case.favorite.IsRecipeFavoriteUseCase
import com.example.homeworkstbc.domain.use_case.favorite.RemoveRecipeFromFavoritesUseCase
import com.example.homeworkstbc.domain.use_case.recipe.GetRecipeDetailsUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import com.example.homeworkstbc.presentation.mapper.toDomain
import com.example.homeworkstbc.presentation.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val addRecipeToFavoritesUseCase: AddRecipeToFavoritesUseCase,
    private val removeRecipeFromFavoritesUseCase: RemoveRecipeFromFavoritesUseCase,
    private val isRecipeFavoriteUseCase: IsRecipeFavoriteUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase
) : BaseViewModel<DetailsState, DetailsSideEffect>(DetailsState()) {

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetRecipeDetails -> getRecipeDetails(event.recipeId)
            is DetailsEvent.OnFavoriteClicked -> onFavoriteClicked()
            is DetailsEvent.OpenUrlClicked -> openUrl(event.url)
        }
    }

    private fun openUrl(url: String) {
        emitSideEffect(DetailsSideEffect.OpenUrl(url))
    }

    private fun onFavoriteClicked() {
        viewModelScope.launch {
            val currentState = state.value
            val recipe = currentState.recipeDetails ?: return@launch
            val userId = getCurrentUserIdUseCase()

            if (userId == null) {
                emitSideEffect(DetailsSideEffect.ShowError)
                return@launch
            }

            if (currentState.isFavorite) removeRecipeFromFavoritesUseCase(recipe.id, userId)
            else addRecipeToFavoritesUseCase(recipe.toDomain(), userId)
        }
    }

    fun getRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            val userId = getCurrentUserIdUseCase()
            if (userId != null) {
                isRecipeFavoriteUseCase(recipeId, userId).collect { isFavorite ->
                    updateState { it.copy(isFavorite = isFavorite) }
                }
            }
        }
        viewModelScope.launch {
            getRecipeDetailsUseCase(recipeId).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        emitSideEffect(DetailsSideEffect.ShowError)
                        updateState { it.copy(isLoading = false) }
                    }

                    is Resource.Loader -> updateState { it.copy(isLoading = resource.isLoading) }
                    is Resource.Success -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                recipeDetails = resource.data.toPresentation()
                            )
                        }
                    }
                }
            }
        }
    }
}