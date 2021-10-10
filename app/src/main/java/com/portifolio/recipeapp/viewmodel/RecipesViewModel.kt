package com.portifolio.recipeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.portifolio.recipeapp.util.Constants
import com.portifolio.recipeapp.util.Constants.Companion.API_KEY
import com.portifolio.recipeapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.portifolio.recipeapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.portifolio.recipeapp.util.Constants.Companion.DEFAULT_NUMBER_RECIPES_SHOW
import com.portifolio.recipeapp.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.portifolio.recipeapp.util.Constants.Companion.QUERY_API_KEY
import com.portifolio.recipeapp.util.Constants.Companion.QUERY_DIET
import com.portifolio.recipeapp.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.portifolio.recipeapp.util.Constants.Companion.QUERY_NUMBER
import com.portifolio.recipeapp.util.Constants.Companion.QUERY_TYPE

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String>{
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_NUMBER_RECIPES_SHOW
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }


}