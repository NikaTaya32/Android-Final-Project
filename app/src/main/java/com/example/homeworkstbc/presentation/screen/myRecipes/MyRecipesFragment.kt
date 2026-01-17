package com.example.homeworkstbc.presentation.screen.myRecipes

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homeworkstbc.databinding.FragmentMyRecipesBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.screen.myRecipes.adapter.MyRecipesAdapter
import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecipesFragment : BaseFragment<FragmentMyRecipesBinding>(FragmentMyRecipesBinding::inflate) {
    private val adapter: MyRecipesAdapter by lazy { MyRecipesAdapter() { onClick(it) } }
    private val viewModel: MyRecipesViewModel by viewModels()

    override fun bind() = with(binding){
        setUpAdapter()
        viewModel.onEvent(MyRecipesEvent.LoadMyRecipes)
        observe()
    }

    private fun onClick(recipe: MyRecipeModel) {
        val action = MyRecipesFragmentDirections.actionMyRecipesFragmentToMyRecipeDetailsFragment(recipe)
        findNavController().navigate(action)
    }

    private fun observe() {
        collectFlow(viewModel.state) { state ->
            adapter.submitList(state.myRecipes)
        }
    }

    private fun setUpAdapter() = with(binding){
        rvMyRecipes.adapter = adapter
        rvMyRecipes.layoutManager = GridLayoutManager(requireContext(), 2)
    }


}