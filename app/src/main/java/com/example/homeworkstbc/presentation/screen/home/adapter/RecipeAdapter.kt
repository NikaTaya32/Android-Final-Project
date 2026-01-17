package com.example.homeworkstbc.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homeworkstbc.databinding.RecipeItemBinding
import com.example.homeworkstbc.presentation.screen.home.model.RecipeModel

class RecipeAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<RecipeModel, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind()
    }

    inner class RecipeViewHolder(
        private val binding: RecipeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() = with(binding){
            val recipe = getItem(bindingAdapterPosition)
            tvTitle.text = recipe.title
            tvPublisher.text = recipe.publisher

            imgRecipe.load(recipe.imageUrl) {
                crossfade(true)
            }

            root.setOnClickListener {
                onItemClick(recipe.id)
            }
        }
    }
}
private class RecipeDiffCallback : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem == newItem
    }
}