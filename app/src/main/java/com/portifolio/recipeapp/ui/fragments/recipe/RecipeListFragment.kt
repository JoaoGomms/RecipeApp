package com.portifolio.recipeapp.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.portifolio.recipeapp.adapters.RecipesAdapter
import com.portifolio.recipeapp.databinding.FragmentRecipeListBinding
import com.portifolio.recipeapp.util.Constants.Companion.API_KEY
import com.portifolio.recipeapp.util.NetworkResult
import com.portifolio.recipeapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private var _binding : FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val recipesAdapter by lazy { RecipesAdapter() }
    private val viewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipeListBinding.inflate(layoutInflater)

        setupRecyclerView()
        requestApiData()

        return binding.root
    }

    private fun requestApiData(){
        viewModel.getRecipes(applyQueries())
        viewModel.getRecipesResponse().observe(viewLifecycleOwner, {
            response -> when(response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { recipesAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun applyQueries(): HashMap<String, String>{
        val queries: HashMap<String, String> = HashMap()

        queries["number"] = "50"
        queries["apiKey"] = API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        return queries
    }


    private fun setupRecyclerView(){
        binding.shimmerRecyclerView.adapter = recipesAdapter
        binding.shimmerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()

    }

    private fun showShimmerEffect(){
        binding.shimmerRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect(){
        binding.shimmerRecyclerView.hideShimmer()
    }

}