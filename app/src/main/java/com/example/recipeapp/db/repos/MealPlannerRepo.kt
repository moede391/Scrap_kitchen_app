package com.example.recipeapp.db.repos

import androidx.annotation.WorkerThread
import com.example.recipeapp.db.dao.MealPlannerEntDAO
import com.example.recipeapp.db.entities.MealPlannerEnt
import kotlinx.coroutines.flow.Flow

/**
 * Meal planner repository
 * @author Ariana,Conor,Cordell,Derek
 * @param dao the database access object for the repo
 * */
class MealPlannerRepo(private val dao: MealPlannerEntDAO) {
    private val mealPlanner: Flow<List<MealPlannerEnt>> = dao.getMealPlanner()

    /**
     * Gets the meal planner property
     * @return the meal planner
     * */
    fun getMealPlanner(): Flow<List<MealPlannerEnt>> {
        return mealPlanner
    }

    /**
     * Calls the dao update meal planner
     * @param recipeId id of the recipe
     * @param index of the meal planner table
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun updateMealPlanner(recipeId: Int, index: Int) {
        dao.updateMealPlanner(recipeId, index)
    }
}