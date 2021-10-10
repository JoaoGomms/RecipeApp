package com.portifolio.recipeapp.ui.fragments.recipe.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.portifolio.recipeapp.databinding.RecipesBottomSheetBinding
import com.portifolio.recipeapp.util.Constants
import com.portifolio.recipeapp.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.portifolio.recipeapp.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RecipesBottomSheet : BottomSheetDialogFragment() {


    private var _binding : RecipesBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by viewModels()

    private var mealTypeChip = Constants.DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = RecipesBottomSheetBinding.inflate(layoutInflater)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, {
            mealTypeChip = it.selectedMealType
            dietTypeChip = it.selectedDietType
            updateChip(it.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(it.selectedDietTypeId, binding.dietTypeChipGroup)
        })

        binding.mealTypeChipGroup.setOnCheckedChangeListener{
            group, selectedChip ->

            val chip = group.findViewById<Chip>(selectedChip)
            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)

            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChip

        }

        binding.dietTypeChipGroup.setOnCheckedChangeListener{
            group, selectedChip ->
            val chip = group.findViewById<Chip>(selectedChip)
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)

            dietTypeChip = selectedDietType
            dietTypeChipId = selectedChip
        }


        binding.applyButton.setOnClickListener{

            recipesViewModel.saveMealAndDietType(mealTypeChip, mealTypeChipId, dietTypeChip, dietTypeChipId)
            val action = RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipeListFragment(true)
            findNavController().navigate(action)

        }



        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {

        if (chipId != 0){
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true

            }catch (e: Exception){
                Log.d("RecipesBottomSheet", e.message.toString())
            }
        }

    }

}