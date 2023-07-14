package com.example.recipeapp.ui.mealPlanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentMealPlannerBinding
import com.example.recipeapp.db.entities.RecipeEnt
import com.example.recipeapp.ui.recipe.RecipeViewModel

/**
 * Meal planner fragment to show the current meal planner
 * @author Ariana,Conor,Cordell,Derek
 * */
class MealPlannerFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMealPlannerBinding
    private var buttonList: MutableList<Button> = mutableListOf()

    private val mealPlannerViewModel: MealPlannerViewModel by activityViewModels { MealPlannerViewModel.Factory }
    private val recipeViewModel: RecipeViewModel by activityViewModels { RecipeViewModel.Factory }

    /**
     * Creates a binding and sets up the layout
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentMealPlannerBinding.inflate(inflater)
        binding = fragmentBinding
        buttonList.clear()
        buttonList.add(binding.dinnerMon)
        buttonList.add(binding.dinnerTue)
        buttonList.add(binding.dinnerWed)
        buttonList.add(binding.dinnerThu)
        buttonList.add(binding.dinnerFri)
        buttonList.add(binding.dinnerSat)
        buttonList.add(binding.dinnerSun)

        mealPlannerViewModel.items.observe(viewLifecycleOwner) {
            mealPlannerViewModel.weekRecipes.clear()
            for (i in 0 until buttonList.size) {
                val recipe = recipeViewModel.getRecipeById(it[i].dinner_recipe)
                if (recipe != null){
                    buttonList[i].setOnClickListener(this)
                    mealPlannerViewModel.weekRecipes.add(recipe)
                    buttonList[i].text = recipe.name
                } else {
                    buttonList[i].setOnClickListener { findNavController().navigate(R.id.action_mealPlannerFragment_to_recipeListFragment) }
                    mealPlannerViewModel.weekRecipes.add(RecipeEnt(0,"Blank","Blank",1,"Blank","Blank",4,"Blank","Blank"))
                    buttonList[i].text = getString(R.string.no_recipe)
                }


            }
        }
        return fragmentBinding.root
    }

    /**
     * Overrides the onClick which navigates to the recipe each button is connected to
     * @param view the button that was clicked
     * */
    override fun onClick(view: View) {
        val recipe = mealPlannerViewModel.weekRecipes[buttonList.indexOf(view)]
        recipeViewModel.setCurRecipe(recipe)
        findNavController().navigate(R.id.action_mealPlannerFragment_to_recipeFragment)
    }
}