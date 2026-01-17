package com.example.homeworkstbc.presentation.screen.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homeworkstbc.databinding.ItemFavoriteBinding
import com.example.homeworkstbc.presentation.screen.home.model.RecipeModel

class FavoritesAdapter(private val onClickListener: (String) -> Unit): ListAdapter<RecipeModel, FavoritesAdapter.FavoritesViewHolder>(FavoritesDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoritesViewHolder,
        position: Int
    ) {
        holder.bind()
    }

    inner class FavoritesViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val recipe = getItem(bindingAdapterPosition)
            tvRecipeTitle.text = recipe.title
            ivRecipeImage.load(recipe.imageUrl)
            root.setOnClickListener {
                onClickListener.invoke(recipe.id)
            }
        }
    }
}

private class FavoritesDiffUtil : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(
        oldItem: RecipeModel,
        newItem: RecipeModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RecipeModel,
        newItem: RecipeModel
    ): Boolean {
        return oldItem == newItem
    }

}