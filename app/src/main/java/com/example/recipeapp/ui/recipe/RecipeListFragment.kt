package com.example.recipeapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRecipeListBinding
import com.example.recipeapp.db.entities.RecipeEnt

/**
 * A fragment to display a list of recipes
 * @author Ariana,Conor,Cordell,Derek
 * */
class RecipeListFragment : Fragment(), RecipeListAdapter.OnRecipeSelected {

    private val recipeViewModel: RecipeViewModel by activityViewModels { RecipeViewModel.Factory }
    private lateinit var binding: FragmentRecipeListBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeListAdapter

    /**
     * binds the recycler view to the recipe list adapter
     * */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentRecipeListBinding.inflate(inflater)
        binding = fragmentBinding
        recyclerView = binding.recipeRecycler
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecipeListAdapter(recipeViewModel.getRecipes(), this)
        recyclerView.adapter = adapter
        return fragmentBinding.root
    }

    /**
     * Implementing the onRecipeSelected function to navigate to a new fragment
     * @param recipe the recipe that was selected
     * */
    override fun onRecipeSelected(recipe: RecipeEnt) {
        recipeViewModel.setCurRecipe(recipe)
        findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment)
    }

}