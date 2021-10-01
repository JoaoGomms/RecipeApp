package com.portifolio.recipeapp.util

class Constants {

    companion object {

        const val API_KEY = "44ff3a5cac554be294952fa18271c3a2"
        const val BASE_URL = "https://api.spoonacular.com/"

        //API Query KeySet
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //Room Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"

    }

}