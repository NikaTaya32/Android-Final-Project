package com.example.homeworkstbc.presentation.screen.details

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.FragmentDetailsBinding
import com.example.homeworkstbc.presentation.common.BaseFragment
import com.example.homeworkstbc.presentation.extension.collectFlow
import com.example.homeworkstbc.presentation.extension.showSnackBar
import com.example.homeworkstbc.presentation.screen.details.adapter.IngredientsAdapter
import com.example.homeworkstbc.presentation.screen.details.model.RecipeDetailsModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri
import androidx.navigation.fragment.navArgs

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private val adapter: IngredientsAdapter by lazy { IngredientsAdapter() }
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun bind() {
        viewModel.onEvent(DetailsEvent.GetRecipeDetails(args.recipeId))
        observe()
    }

    override fun listeners() = with(binding) {
        arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnFavorite.setOnClickListener {
            viewModel.onEvent(DetailsEvent.OnFavoriteClicked)
        }
    }

    private fun observe() = with(binding) {
        collectFlow(viewModel.sideEffect) { effect ->
            when (effect) {
                is DetailsSideEffect.ShowError -> root.showSnackBar(getString(R.string.details_not_found))
                is DetailsSideEffect.OpenUrl -> openUrlInBrowser(effect.url)
            }
        }

        collectFlow(viewModel.state) {
            if (it.recipeDetails != null) loadDetails(it.recipeDetails)

            val drawableRes =
                if (it.isFavorite) R.drawable.ic_favorite_filled_red
                else R.drawable.favourite_ic

            btnFavorite.setImageResource(drawableRes)
        }
    }

    private fun loadDetails(recipeDetails: RecipeDetailsModel) = with(binding) {
        imgRecipe.load(recipeDetails.imageUrl)

        rvIngredients.adapter = adapter
        rvIngredients.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter.submitList(recipeDetails.ingredients)

        btnRecipeUrl.setOnClickListener {
            viewModel.onEvent(DetailsEvent.OpenUrlClicked(recipeDetails.sourceUrl))
        }

        tvTitle.text = recipeDetails.title
        tvPublisher.text = getString(R.string.recipe_by).plus(recipeDetails.publisher)
        tvIngredients.text =
            recipeDetails.ingredients.size.toString().plus(getString(R.string.ingredients))
    }

    private fun openUrlInBrowser(url: String) = with(binding) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())

        try {
            startActivity(intent)
        } catch (e: Exception) {
            root.showSnackBar(getString(R.string.browser_not_found))
        }
    }

}