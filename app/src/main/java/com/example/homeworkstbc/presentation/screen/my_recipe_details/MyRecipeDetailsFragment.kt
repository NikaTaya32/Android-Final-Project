package com.example.homeworkstbc.presentation.screen.my_recipe_details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.FragmentMyRecipeDetailsBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.extension.showSnackBar
import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel
import com.example.homeworkstbc.presentation.screen.my_recipe_details.adapter.MyRecipeDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecipeDetailsFragment : BaseFragment<FragmentMyRecipeDetailsBinding>(FragmentMyRecipeDetailsBinding::inflate) {
    private val viewModel: MyRecipeDetailsViewModel by viewModels()
    private val args: MyRecipeDetailsFragmentArgs by navArgs()
    private val adapter: MyRecipeDetailsAdapter by lazy { MyRecipeDetailsAdapter() }

    override fun bind() {
        setUpAdapter()
        viewModel.onEvent(MyRecipeDetailsEvent.LoadMyRecipe(args.recipeItem))
        observe()
    }

    override fun listeners() = with(binding){
        arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnDelete.setOnClickListener {
            viewModel.onEvent(MyRecipeDetailsEvent.DeleteMyRecipe)
        }
    }

    private fun observe() = with(binding){
        collectFlow(viewModel.state) {
            if (it.recipe != null) {
                adapter.submitList(it.recipe.ingredients)
                loadDetails(it.recipe)
            }
        }
        collectFlow(viewModel.sideEffect) {
            when(it) {
                MyRecipeDetailsSideEffect.NavigateBack -> findNavController().navigateUp()
                MyRecipeDetailsSideEffect.ShowError -> root.showSnackBar(getString(R.string.recipe_id_not_found))
            }
        }
    }

    private fun loadDetails(recipe: MyRecipeModel) = with(binding) {
        tvInstructions.text = recipe.instructions
        tvTitle.text = recipe.title
        tvIngredients.text = recipe.ingredients.size.toString().plus(getString(R.string.ingredients))
    }

    private fun setUpAdapter() = with(binding){
        rvIngredients.adapter = adapter
        rvIngredients.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}