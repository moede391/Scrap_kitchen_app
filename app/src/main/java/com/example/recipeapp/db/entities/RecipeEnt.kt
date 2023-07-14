package com.example.recipeapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Recipe entity data class
 * @author Ariana,Conor,Cordell,Derek
 * */
@Entity(tableName = "recipe_table")
data class RecipeEnt(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val name: String,
    @ColumnInfo(name = "description") val description: String,
    //Regions 1-7 represent the 7 continents
    // 1= north america, 2 = south america, 3 = africa , 4 = europe, 5= Asia, 6 = oceania, 7 = antarctica
    @ColumnInfo(name = "region") val region: Int,
    @ColumnInfo(name = "ingredients") val ingredients: String,
    @ColumnInfo(name = "method") val method: String,
    @ColumnInfo(name = "servings") val servings: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "recipeShopping") val recipeShopping: String,
)