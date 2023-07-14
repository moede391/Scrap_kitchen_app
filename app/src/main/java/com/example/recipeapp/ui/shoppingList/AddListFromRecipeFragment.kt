package com.example.recipeapp.ui.shoppingList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.Util
import com.example.recipeapp.databinding.FragmentAddListFromRecipeBinding
import com.example.recipeapp.ui.recipe.RecipeViewModel

/**
 * Displays ingredients from a recipe to be added to the shopping list
 * @author Ariana,Conor,Cordell,Derek
 * */
class AddListFromRecipeFragment : Fragment() {

    private val recipeViewModel: RecipeViewModel by activityViewModels { RecipeViewModel.Factory }
    private val shoppingListViewModel: ShoppingListViewModel by activityViewModels { ShoppingListViewModel.Factory }

    private lateinit var binding: FragmentAddListFromRecipeBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShoppingListItemAdapter

    /**
     * creates a binding and sets the recycler view
     * */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentAddListFromRecipeBinding.inflate(inflater)
        binding = fragmentBinding
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = ShoppingListItemAdapter(
            Util.ingredientsToList(recipeViewModel.getCurRecipe()!!.recipeShopping),
            shoppingListViewModel
        )
        recyclerView.adapter = adapter
        binding.addIngredToList.setOnClickListener {
            shoppingListViewModel.mergeItems(adapter.getItems())
            requireActivity().onBackPressed()
        }
        return fragmentBinding.root
    }

}