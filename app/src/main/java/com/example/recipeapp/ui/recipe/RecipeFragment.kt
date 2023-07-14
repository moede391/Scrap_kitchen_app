package com.example.recipeapp.ui.recipe

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.MainActivity
import com.example.recipeapp.R
import com.example.recipeapp.Util.Companion.stringToFormattedList
import com.example.recipeapp.consts.Emojis
import com.example.recipeapp.databinding.FragmentRecipeBinding
import com.example.recipeapp.db.entities.RecipeEnt

/**
 * Recipe fragment for any recipe, sets up the binding
 * @author Ariana,Conor,Cordell,Derek
 * */
class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val emojis = Emojis.Companion


    private val recipeViewModel: RecipeViewModel by activityViewModels { RecipeViewModel.Factory }
    private lateinit var recipe: RecipeEnt

    /**
     * Sets the binding fields to the current recipe in the view model
     * */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipe = recipeViewModel.getCurRecipe()!!
        val fragmentBinding = FragmentRecipeBinding.inflate(inflater)

        (activity as MainActivity).supportActionBar?.title = recipe.name

        binding = fragmentBinding
        binding.flag.text = emojis.getEmoji(recipe.country)
        binding.titleTxt.text = recipe.name.replace("(", "\n(")
        binding.descriptionTxt.text = recipe.description
        binding.ingredientsTxt.text = stringToFormattedList(recipe.ingredients)
        binding.methodTxt.text = stringToFormattedList(recipe.method)
        binding.servesTxt.text = "Serves ${recipe.servings}"

        binding.menuButton.setOnClickListener {
            //show floating action menu buttons
            if(binding.addShoppingList.visibility == View.GONE){
                binding.addShoppingList.visibility = View.VISIBLE
                binding.addMealPlan.visibility = View.VISIBLE
                binding.menuButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }else{
                binding.addShoppingList.visibility = View.GONE
                binding.addMealPlan.visibility = View.GONE
                binding.menuButton.setImageResource(R.drawable.ic_baseline_menu_24)
            }

        }
        binding.addShoppingList.setOnClickListener {
            findNavController().navigate(R.id.action_recipeFragment_to_addListFromRecipeFragment)
        }
        binding.addMealPlan.setOnClickListener {
            recipeViewModel.addMealPlanner(childFragmentManager)
        }

        return fragmentBinding.root
    }

    /**
     * Enables the toolbar menu
     * @param savedInstanceState bundle of state
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

}