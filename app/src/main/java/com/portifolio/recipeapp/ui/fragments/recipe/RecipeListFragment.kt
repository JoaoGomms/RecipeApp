package com.portifolio.recipeapp.ui.fragments.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.portifolio.recipeapp.adapters.RecipesAdapter
import com.portifolio.recipeapp.databinding.FragmentRecipeListBinding
import com.portifolio.recipeapp.util.NetworkResult
import com.portifolio.recipeapp.util.observeOnce
import com.portifolio.recipeapp.viewmodel.MainViewModel
import com.portifolio.recipeapp.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    private val recipesAdapter by lazy { RecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()
    private val recipesViewModel: RecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipeListBinding.inflate(layoutInflater)

        setupRecyclerView()

        readDatabase()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.shimmerRecyclerView.adapter = recipesAdapter
        binding.shimmerRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()

    }

    private fun readDatabase() {
       lifecycleScope.launch{
           mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { database ->
               if (database.isNotEmpty()) {
                   Log.d("RecipesListFragment", "Database Called")
                   recipesAdapter.setData(database[0].foodRecipe)
                   hideShimmerEffect()
               } else {
                   requestApiData()
               }
           })
       }
    }

    private fun requestApiData() {
        Log.d("RecipesListFragment", "Api Called")
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.getRecipesResponse().observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { recipesAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromChache()
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun loadDataFromChache(){
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, {
                    database -> if (database.isNotEmpty()) {
                recipesAdapter.setData(database[0].foodRecipe)
            }
            })
        }
    }




    private fun showShimmerEffect() {
        binding.shimmerRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.shimmerRecyclerView.hideShimmer()
    }

}