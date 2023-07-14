package com.example.recipeapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeapp.db.entities.RecipeEnt

/**
 * Recipe database access object
 * @author Ariana,Conor,Cordell,Derek
 * */
@Dao
interface RecipeEntDAO {
    /**
     * Gets a list of recipe based on the region id
     * @param region the region id
     * @return list of recipe entities that match the region id
     * */
    @Query("SELECT * FROM recipe_table WHERE region = (:region)")
    fun getRecipeByCountry(region: Int): List<RecipeEnt>

    /**
     * Gets all recipes
     * @return list of all recipes
     * */
    @Query("SELECT * FROM recipe_table ORDER BY id ASC")
    fun getRecipes(): List<RecipeEnt>

    /**
     * Gets a recipe that matches the id
     * @param id of the recipe
     * @return the recipe that matches the id
     * */
    @Query("SELECT * FROM recipe_table WHERE id = (:id)")
    fun getRecipeById(id: Int): RecipeEnt

    /**
     * Inserts a recipe into the db
     * @param recipe the recipe to insert
     * */
    @Insert
    fun insertRecipe(recipe: RecipeEnt)
}