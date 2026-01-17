package com.example.homeworkstbc.presentation.screen.my_recipe_details

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.use_case.my_recipe.DeleteOwnRecipeUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecipeDetailsViewModel @Inject constructor(
    private val deleteOwnRecipeUseCase: DeleteOwnRecipeUseCase
) : BaseViewModel<MyRecipeDetailsState, MyRecipeDetailsSideEffect>(MyRecipeDetailsState()) {

    fun onEvent(event: MyRecipeDetailsEvent) {
        when (event) {
            is MyRecipeDetailsEvent.LoadMyRecipe -> loadMyRecipe(event.recipe)
            is MyRecipeDetailsEvent.DeleteMyRecipe -> deleteMyRecipe()
        }
    }

    private fun deleteMyRecipe() {
        val recipeId = state.value.recipe?.id

        if (recipeId == null) {
            emitSideEffect(MyRecipeDetailsSideEffect.ShowError)
            return
        }

        viewModelScope.launch {
            deleteOwnRecipeUseCase(recipeId)
            emitSideEffect(MyRecipeDetailsSideEffect.NavigateBack)
        }
    }

    private fun loadMyRecipe(recipe: MyRecipeModel) {
        updateState { it.copy(recipe = recipe) }
    }
}