package com.example.recipeapp.db.repos

import androidx.annotation.WorkerThread
import com.example.recipeapp.db.dao.RecipeEntDAO
import com.example.recipeapp.db.entities.RecipeEnt

/**
 * Recipe repository
 * @author Ariana,Conor,Cordell,Derek
 * @param dao the database access object for the repo
 * */
class RecipeRepo(private val dao: RecipeEntDAO) {
    private val recipes: List<RecipeEnt> = dao.getRecipes()

    /**
     * Returns the recipe property
     * @return list of recipe entities
     * */
    fun getRecipes(): List<RecipeEnt> {
        return recipes
    }

    /**
     * uses the dao to insert a recipe into the db
     * @param recipe to insert
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertRecipe(recipe: RecipeEnt) {
        dao.insertRecipe(recipe)
    }

    /**
     * uses the dao to get the recipe based off region id
     * @param region value
     * @return list of recipe entities with the same region value
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getRecipeByCountry(region: Int): List<RecipeEnt> {
        return dao.getRecipeByCountry(region)
    }

    /**
     * uses the dao to get a recipe based off an id
     * @param id of the recipe
     * @return recipe with an id
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getRecipeById(id: Int): RecipeEnt {
        return dao.getRecipeById(id)
    }


}