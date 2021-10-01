package com.portifolio.recipeapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.portifolio.recipeapp.data.db.dao.RecipesDao
import com.portifolio.recipeapp.data.db.entity.RecipesEntity

@Database(
    entities = [ RecipesEntity::class ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao() : RecipesDao

}