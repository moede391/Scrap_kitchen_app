package com.example.recipeapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * MealPlanner Entity data class
 * @author Ariana,Conor,Cordell,Derek
 */
@Entity(tableName = "meal_planner_table")
data class MealPlannerEnt(
    @PrimaryKey(autoGenerate = false) var id: Int,
    @ColumnInfo(name = "dinner_recipe") val dinner_recipe: Int
)