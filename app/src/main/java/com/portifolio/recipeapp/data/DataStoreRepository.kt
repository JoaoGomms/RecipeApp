package com.portifolio.recipeapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.portifolio.recipeapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.portifolio.recipeapp.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.portifolio.recipeapp.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.portifolio.recipeapp.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.portifolio.recipeapp.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.portifolio.recipeapp.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.portifolio.recipeapp.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys{
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
    }



    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

    suspend fun saveMealAndDietType(selectedMealType: String,
                            selectedMealTypeId: Int,
                            selectedDietType: String,
                            selectedDietTypeId: Int){

        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = selectedMealType
            preferences[PreferenceKeys.selectedMealTypeId] = selectedMealTypeId
            preferences[PreferenceKeys.selectedDietType] = selectedDietType
            preferences[PreferenceKeys.selectedDietTypeId] = selectedDietTypeId
        }

    }

    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data.catch {
        exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        } else {
            throw exception
        }
    } . map { preferences ->
        val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
        val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: 0
        val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
        val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId] ?: 0
        MealAndDietType(
            selectedMealType, selectedMealTypeId, selectedDietType, selectedDietTypeId
        )
    }

}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int,

)