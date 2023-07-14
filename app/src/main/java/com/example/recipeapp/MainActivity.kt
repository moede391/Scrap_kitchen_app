package com.example.recipeapp

import android.os.Bundle
import android.os.Debug
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.db.entities.RecipeEnt
import com.example.recipeapp.ui.mealPlanner.MealPlannerViewModel
import com.example.recipeapp.ui.recipe.RecipeViewModel
import com.example.recipeapp.ui.shoppingList.ShoppingListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception

/**
 * @author Ariana,Conor,Cordell,Derek
 * Main activity for the program
 * */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val recipeViewModel: RecipeViewModel by viewModels { RecipeViewModel.Factory }
    private val shoppingListViewModel: ShoppingListViewModel by viewModels { ShoppingListViewModel.Factory }
    private val mealPlannerViewModel: MealPlannerViewModel by viewModels { MealPlannerViewModel.Factory }

    /**
     * Inflates the layout and sets up the navigation controller along with loading data in view models
     * @param savedInstanceState
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNav
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.shoppingListFragment,R.id.regionSelectFragment, R.id.mealPlannerFragment
            )
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        shoppingListViewModel.items.observeForever{}
        mealPlannerViewModel.items.observeForever {}
        recipeViewModel.getRecipes()

//        seeRecipes(recipeViewModel.getRecipes())
    }

    /**
     * Removes observers from from live data
     * */
    override fun onStart() {
        super.onStart()
        shoppingListViewModel.items.removeObservers(this)
        mealPlannerViewModel.items.removeObservers(this)
    }

    /**
     * Setting up the navigation back button for the fragment container
     * */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


//    private fun seeRecipes(recipes: List<RecipeEnt>) {
//        val categoryList = mutableListOf<String>()
//        val metricList = mutableListOf<String>()
//        val nameList = mutableListOf<String>()
//        recipes.forEach {
//            try {
//                Util.ingredientsToList(it.recipeShopping)
//            } catch (e : Exception){
//                println(it.name)
//            }
//
//            val ingredientList = it.recipeShopping.split(",")
//            if (ingredientList.size % 4 != 0) {
//                println("Recipe id " + it.name)
//            } else {
//                for (i in ingredientList.indices step 4) {
//                    val cat = ingredientList[i + 2].trim().lowercase()
//                    val metric = ingredientList[i + 1].trim().lowercase()
//                    val name = ingredientList[i + 3].trim().lowercase()
//                    if (!categoryList.contains(cat)) {
//                        categoryList.add(cat)
//                    }
//                    if (!nameList.contains(name)) {
//                        nameList.add(name)
//                    }
//                    if (!metricList.contains(metric)) {
//                        metricList.add(metric)
//                    }
//                }
//            }
//        }
//        println(nameList.sorted())
//        println(metricList)
//        println(categoryList)
//    }
}