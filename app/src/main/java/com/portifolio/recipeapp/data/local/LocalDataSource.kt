package com.portifolio.recipeapp.data.local

import com.portifolio.recipeapp.data.db.dao.RecipesDao
import com.portifolio.recipeapp.data.db.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: RecipesDao){

    fun readDatabase(): Flow<List<RecipesEntity>> {
        return dao.readRecipes()
    }

    suspend fun insertRecipes(recipes: RecipesEntity){
        dao.insertRecipes(recipes)
    }

}