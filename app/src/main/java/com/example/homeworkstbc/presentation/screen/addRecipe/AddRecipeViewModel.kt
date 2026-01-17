package com.example.homeworkstbc.presentation.screen.addRecipe

import androidx.lifecycle.viewModelScope
import com.example.homeworkstbc.domain.use_case.my_recipe.AddOwnRecipeUseCase
import com.example.homeworkstbc.domain.use_case.my_recipe.ValidateRecipeInputUseCase
import com.example.homeworkstbc.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddOwnRecipeUseCase,
    private val validateRecipeInputUseCase: ValidateRecipeInputUseCase
) : BaseViewModel<AddRecipeState, AddRecipeSideEffect>(AddRecipeState()) {

    fun onEvent(event: AddRecipeEvent) {
        when (event) {
            is AddRecipeEvent.AddIngredient -> addIngredient()
            is AddRecipeEvent.RemoveIngredient -> removeIngredient(event.position)
            is AddRecipeEvent.TitleChanged -> updateTitle(event.text)
            is AddRecipeEvent.InstructionsChanged -> updateInstructions(event.text)
            is AddRecipeEvent.IngredientChanged -> updateIngredient(event.position, event.text)
            is AddRecipeEvent.SaveRecipe -> saveRecipe(event.title, event.instructions, event.ingredients)
        }
    }

    private fun addIngredient() {
        updateState { currentState ->
            val newIngredients = currentState.ingredients.toMutableList().apply { add("") }
            currentState.copy(ingredients = newIngredients)
        }
    }

    private fun removeIngredient(position: Int) {
        updateState { currentState ->
            if (currentState.ingredients.size > 1) {
                val newIngredients = currentState.ingredients.toMutableList().apply { removeAt(position) }
                currentState.copy(ingredients = newIngredients)
            } else {
                currentState
            }
        }
    }

    private fun updateTitle(text: String) {
        updateState { it.copy(title = text) }
    }

    private fun updateInstructions(text: String) {
        updateState { it.copy(instructions = text) }
    }

    private fun updateIngredient(position: Int, text: String) {
        updateState { currentState ->
            val newIngredients = currentState.ingredients.toMutableList().apply {
                if (position in indices) {
                    this[position] = text
                }
            }
            currentState.copy(ingredients = newIngredients)
        }
    }

    private fun saveRecipe(title: String, instructions: String, ingredients: List<String>) {
        viewModelScope.launch {
            val validation = validateRecipeInputUseCase(title, ingredients)
            if (!validation) {
                emitSideEffect(AddRecipeSideEffect.ShowError)
                return@launch
            }
            addRecipeUseCase(title, instructions, ingredients)
            emitSideEffect(AddRecipeSideEffect.SaveSuccess)
        }
    }
}