package com.example.recipeapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeapp.db.entities.MealPlannerEnt
import kotlinx.coroutines.flow.Flow


/**
 * MealPlanner database access object
 * @author Ariana,Conor,Cordell,Derek
 */
@Dao
interface MealPlannerEntDAO {
    /**
     * Gets the meal planner
     * @return a list of meal planner entities
     * */
    @Query("SELECT * FROM meal_planner_table")
    fun getMealPlanner(): Flow<List<MealPlannerEnt>>

    /**
     * Updates a entry in the meal planner
     * @param recipeId the id of the recipe
     * @param index the index to replace the current recipe id
     * */
    @Query("UPDATE meal_planner_table SET dinner_recipe = (:recipeId) WHERE id = (:index)")
    fun updateMealPlanner(recipeId: Int, index: Int)

    /**
     * Sets the meal planner to the mealPlanner param
     * @param mealPlanner the list to replace the current meal planner
     * */
    @Insert
    fun setMealPlanner(mealPlanner: List<MealPlannerEnt>)
}