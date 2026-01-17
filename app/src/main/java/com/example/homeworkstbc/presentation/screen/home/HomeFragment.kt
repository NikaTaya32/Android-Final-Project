package com.example.homeworkstbc.presentation.screen.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.FragmentHomeBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.extension.showSnackBar
import com.example.homeworkstbc.presentation.screen.home.adapter.RecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private val adapter: RecipeAdapter by lazy { RecipeAdapter() { onItemClick(it) } }

    override fun bind() {
        observe()
        setUpAdapter()
    }

    override fun listeners() = with(binding) {
        clickOnLogOut()
        btnSearch.setOnClickListener {
            val recipe = etSearch.text.toString()
            viewModel.onEvent(HomeEvent.SearchRecipes(recipe))
        }
    }

    private fun clickOnLogOut() = with(binding) {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_logout  -> {
                    viewModel.onEvent(HomeEvent.LogOut)
                    true
                }
                else -> false
            }
        }
    }
    private fun onItemClick(recipeId: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(recipeId = recipeId)
        findNavController().navigate(action)
    }

    private fun setUpAdapter() = with(binding){
        rvRecipes.adapter = adapter
        rvRecipes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observe() = with(binding){
        collectFlow(viewModel.sideEffect) {
            when(it) {
                is HomeSideEffect.NavigateToAuth -> {
                    findNavController().navigate(R.id.nav_graph) {
                        popUpTo(R.id.nav_graph) {
                            inclusive = true
                        }
                    }
                }
                is HomeSideEffect.ShowError -> {
                    root.showSnackBar(getString(R.string.no_recipes_found_for_your_query_please_try_again))
                    adapter.submitList(emptyList())
                }
            }
        }
        collectFlow(viewModel.state) {
            if(it.recipes.isNotEmpty()) adapter.submitList(it.recipes)
            progressBar.isVisible = it.loader
        }
    }

}