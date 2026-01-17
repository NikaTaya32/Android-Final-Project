package com.example.homeworkstbc.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.model.Resource
import com.example.homeworkstbc.domain.use_case.auth.LogOutUserUseCase
import com.example.homeworkstbc.domain.use_case.recipe.SearchRecipesUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import com.example.homeworkstbc.presentation.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOutUserUseCase: LogOutUserUseCase,
    private val searchRecipesUseCase: SearchRecipesUseCase
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LogOut -> logOut()
            is HomeEvent.SearchRecipes -> searchRecipes(event.recipe)
        }
    }

    private fun logOut() {
        viewModelScope.launch {
            logOutUserUseCase.invoke()
            emitSideEffect(HomeSideEffect.NavigateToAuth)
        }
    }

    private fun searchRecipes(recipe: String) {
        viewModelScope.launch {
            searchRecipesUseCase.invoke(recipe).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        updateState { it.copy(loader = false) }

                        emitSideEffect(HomeSideEffect.ShowError(resource.message.toString()))
                    }

                    is Resource.Loader -> {
                        updateState { it.copy(loader = true) }
                    }

                    is Resource.Success -> {
                        updateState { state ->
                            state.copy(
                                loader = false,
                                recipes = resource.data.map { it.toPresentation() })
                        }
                    }
                }
            }
        }
    }
}