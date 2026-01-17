package com.example.homeworkstbc.presentation.screen.addRecipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkstbc.databinding.ItemIngredientEditBinding

class AddRecipeIngredientsAdapter(
    private val onRemoveClicked: (position: Int) -> Unit,
) : ListAdapter<String, AddRecipeIngredientsAdapter.IngredientViewHolder>(DiffCallback()) {
    inner class IngredientViewHolder(val binding: ItemIngredientEditBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRemoveIngredient.setOnClickListener {
                onRemoveClicked(bindingAdapterPosition)
            }
        }

        fun bind() {
            val ingredient = getItem(bindingAdapterPosition)
            binding.etIngredient.setText(ingredient)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(
            ItemIngredientEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind()
    }
}

private class DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}