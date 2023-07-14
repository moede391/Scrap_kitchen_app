package com.example.recipeapp.ui.mealPlanner

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.recipeapp.R
import com.example.recipeapp.ui.recipe.RecipeViewModel

/**
 * Dialog fragment to show options for meal planner day
 * @author Ariana,Conor,Cordell,Derek
 * */
class MealPlannerSelectDialog : DialogFragment() {

    private val mealPlannerViewModel: MealPlannerViewModel by activityViewModels { MealPlannerViewModel.Factory }
    private val recipeViewModel: RecipeViewModel by activityViewModels { RecipeViewModel.Factory }

    /**
     * Setting up the dialog fragment with an array of days and adding a click handler
     * @param savedInstanceState bundle with state
     * */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.daySelector)
                .setItems(R.array.Days) { _, which ->
                    run {
                        mealPlannerViewModel.updateMealItem(
                            recipeViewModel.getCurRecipe()!!.id,
                            which
                        )
                    }
                }
            builder.create()
        }
    }

    companion object {
        const val TAG = "Select Day"
    }
}