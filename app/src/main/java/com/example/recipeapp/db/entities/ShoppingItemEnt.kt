package com.example.recipeapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Shopping item entity data class
 * @author Ariana,Conor,Cordell,Derek
 * */
@Entity(tableName = "item_table")
data class ShoppingItemEnt(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "item") val item: String,
    @ColumnInfo(name = "amount") var amount: Double,
    @ColumnInfo(name = "metric") val metric: String,
    @ColumnInfo(name = "checked") var checked: Boolean,
    @ColumnInfo(name = "category") var cat: String
)
