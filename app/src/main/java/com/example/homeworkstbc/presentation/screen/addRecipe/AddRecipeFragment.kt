package com.example.homeworkstbc.presentation.screen.addRecipe

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.FragmentAddRecipeBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.extension.showSnackBar
import com.example.homeworkstbc.presentation.screen.addRecipe.adapter.AddRecipeIngredientsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRecipeFragment :
    BaseFragment<FragmentAddRecipeBinding>(FragmentAddRecipeBinding::inflate) {

    private val viewModel: AddRecipeViewModel by viewModels()

    private val ingredientsAdapter: AddRecipeIngredientsAdapter by lazy {
        AddRecipeIngredientsAdapter(
            onRemoveClicked = { position ->
                val currentTexts = readIngredientsFromUI()
                val updatedTexts = currentTexts.toMutableList().apply { removeAt(position) }
                ingredientsAdapter.submitList(updatedTexts)
            }
        )
    }

    override fun bind() {
        setupRecyclerView()
        observeSideEffects()
    }

    override fun listeners() {
        binding.btnAddIngredient.setOnClickListener {
            val currentTexts = readIngredientsFromUI()
            val updatedTexts = currentTexts + ""
            ingredientsAdapter.submitList(updatedTexts)
        }

        binding.btnSave.setOnClickListener {
            saveRecipeData()
        }
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerView.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        ingredientsAdapter.submitList(listOf(""))
    }

    private fun observeSideEffects() {
        collectFlow(viewModel.sideEffect) { sideEffect ->
            when (sideEffect) {
                is AddRecipeSideEffect.ShowError -> {
                    binding.root.showSnackBar(getString(R.string.please_fill_all_fields))
                }

                is AddRecipeSideEffect.SaveSuccess -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun readIngredientsFromUI(): List<String> {
        val ingredients = mutableListOf<String>()
        for (i in 0 until ingredientsAdapter.itemCount) {
            val holder =
                binding.ingredientsRecyclerView.findViewHolderForAdapterPosition(i) as? AddRecipeIngredientsAdapter.IngredientViewHolder
            val text = holder?.binding?.etIngredient?.text?.toString() ?: ""
            ingredients.add(text)
        }
        return ingredients
    }

    private fun saveRecipeData() {
        val title = binding.etTitle.text.toString()
        val instructions = binding.etInstructions.text.toString()
        val ingredients = readIngredientsFromUI()
        viewModel.onEvent(AddRecipeEvent.SaveRecipe(title, instructions, ingredients))
    }
}