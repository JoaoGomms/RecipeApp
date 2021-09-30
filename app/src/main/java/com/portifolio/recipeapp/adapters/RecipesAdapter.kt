package com.portifolio.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.portifolio.recipeapp.databinding.RecipesRowLayoutBinding
import com.portifolio.recipeapp.model.FoodRecipe
import com.portifolio.recipeapp.model.Result
import com.portifolio.recipeapp.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private var recipesList = emptyList<Result>()

    class ViewHolder(val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(result: Result){
                binding.result = result
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = recipesList[position]

        holder.bind(currentRecipe)

    }

    override fun getItemCount() : Int = recipesList.size

    fun setData(newList: FoodRecipe){

        val recipesDiffUtil = RecipesDiffUtil(recipesList, newList.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)

        recipesList = newList.results

        diffUtilResult.dispatchUpdatesTo(this)

    }


}