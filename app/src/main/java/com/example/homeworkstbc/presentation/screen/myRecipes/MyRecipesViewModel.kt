package com.example.homeworkstbc.presentation.screen.myRecipes

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.use_case.my_recipe.GetOwnRecipesUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import com.example.homeworkstbc.presentation.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecipesViewModel @Inject constructor(private val getOwnRecipesUseCase: GetOwnRecipesUseCase): BaseViewModel<MyRecipesState, MyRecipesSideEffect>(MyRecipesState()) {

    fun onEvent(event: MyRecipesEvent) {
        when(event) {
            MyRecipesEvent.LoadMyRecipes -> loadMyRecipes()
        }
    }

    private fun loadMyRecipes() {
        viewModelScope.launch {
            getOwnRecipesUseCase.invoke().collect { recipes ->
                updateState { state ->
                    state.copy(
                        myRecipes = recipes.map { it.toPresentation() }
                    )
                }
            }
        }
    }
}