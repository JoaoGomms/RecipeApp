package com.portifolio.recipeapp.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.portifolio.recipeapp.databinding.FragmentRecipeListBinding

class RecipeListFragment : Fragment() {

    private var _binding : FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentRecipeListBinding.inflate(layoutInflater)

        binding.shimmerRecyclerView.showShimmer()

        return binding.root
    }

}