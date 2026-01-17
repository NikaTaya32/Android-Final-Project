package com.example.homeworkstbc.presentation.screen.myRecipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homeworkstbc.R
import com.example.homeworkstbc.databinding.ItemMyRecipeBinding
import com.example.homeworkstbc.presentation.screen.myRecipes.model.MyRecipeModel

class MyRecipesAdapter(
    private val onItemClick: (MyRecipeModel) -> Unit
) : ListAdapter<MyRecipeModel, MyRecipesAdapter.MyRecipesViewHolder>(MyRecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipesViewHolder {
        val binding = ItemMyRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyRecipesViewHolder, position: Int) {
        holder.bind()
    }

    inner class MyRecipesViewHolder(private val binding: ItemMyRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding){
            val recipe = getItem(bindingAdapterPosition)
            tvRecipeTitle.text = recipe.title
            ivRecipeImage.load(R.mipmap.my_recipe_foreground)

            root.setOnClickListener {
                onItemClick.invoke(recipe)
            }
        }
    }
}

private class MyRecipeDiffCallback : DiffUtil.ItemCallback<MyRecipeModel>() {
    override fun areItemsTheSame(oldItem: MyRecipeModel, newItem: MyRecipeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyRecipeModel, newItem: MyRecipeModel): Boolean {
        return oldItem == newItem
    }
}