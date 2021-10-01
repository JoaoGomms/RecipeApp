package com.portifolio.recipeapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.portifolio.recipeapp.model.FoodRecipe
import com.portifolio.recipeapp.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(

    var foodRecipe: FoodRecipe

) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}