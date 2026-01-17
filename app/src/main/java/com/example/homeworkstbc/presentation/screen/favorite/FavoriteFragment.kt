package com.example.homeworkstbc.presentation.screen.favorite

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homeworkstbc.databinding.FragmentFavoriteBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.screen.favorite.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {
    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter: FavoritesAdapter by lazy { FavoritesAdapter() { onItemClick(it) } }

    override fun bind() {
        setUpAdapter()
        viewModel.onEvent(FavoriteEvent.LoadFavorites)
        observe()
    }

    private fun observe() = with(binding) {
        collectFlow(viewModel.state) { state ->
            val hasFavorites = state.recipes.isNotEmpty()
            tvNoFavorites.isVisible = !hasFavorites
            rvFavorites.isVisible = hasFavorites

            if (hasFavorites) {
                adapter.submitList(state.recipes)
            }
        }
    }

    private fun onItemClick(recipeId: String) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(recipeId = recipeId)
        findNavController().navigate(action)
    }

    private fun setUpAdapter() = with(binding) {
        rvFavorites.adapter = adapter
        rvFavorites.layoutManager = GridLayoutManager(requireContext(), 2)
    }

}