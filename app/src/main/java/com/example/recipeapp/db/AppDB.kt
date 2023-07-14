package com.example.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.db.dao.MealPlannerEntDAO
import com.example.recipeapp.db.dao.RecipeEntDAO
import com.example.recipeapp.db.dao.ShoppingItemEntDAO
import com.example.recipeapp.db.entities.MealPlannerEnt
import com.example.recipeapp.db.entities.RecipeEnt
import com.example.recipeapp.db.entities.ShoppingItemEnt


@Database(
    entities = [RecipeEnt::class, ShoppingItemEnt::class, MealPlannerEnt::class],
    version = 1,
    exportSchema = false
)
/**
 * App database class
 * @author Ariana,Conor,Cordell,Derek
 * */
abstract class AppDB : RoomDatabase() {
    /**
     * gets recipe dao
     * @return recipe dao
     * */
    abstract fun recipeDAO(): RecipeEntDAO
    /**
     * gets shopping item dao
     * @return shopping item dao
     * */
    abstract fun shoppingItemDAO(): ShoppingItemEntDAO
    /**
     * gets meal planner dao
     * @return meal planner dao
     * */
    abstract fun mealPlannerDAO(): MealPlannerEntDAO


    /**
     * The only instance of the database. Implementing Singleton pattern.
     * Checks if the database is null, if the database is null creates new
     * database from assets folder.
     */
    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        /**
         * Returns the only instance of the database.
         * @param context The context of the application.
         * @return The only instance of the database.
         */
        fun getInstance(context: Context): AppDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "recipe_database"
                    ).createFromAsset("database/recipe_table.db").allowMainThreadQueries().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}