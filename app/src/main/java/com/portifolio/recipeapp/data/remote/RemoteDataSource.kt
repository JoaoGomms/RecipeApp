package com.portifolio.recipeapp.data.remote

import com.portifolio.recipeapp.data.api.FoodRecipesApi
import com.portifolio.recipeapp.model.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodRecipesApi: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>) : Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

}