package com.example.recipeapp.db.dao

import androidx.room.*
import com.example.recipeapp.db.entities.ShoppingItemEnt
import kotlinx.coroutines.flow.Flow

/**
 * Shopping item database access object
 * @author Ariana,Conor,Cordell,Derek
 * */
@Dao
interface ShoppingItemEntDAO {
    /**
     * Gets the shopping list
     * @return shopping list
     * */
    @Query("SELECT * FROM item_table")
    fun getShoppingList(): Flow<List<ShoppingItemEnt>>

    /**
     * Removes all checked items in the db
     * */
    @Query("DELETE FROM item_table where checked = 1")
    fun removedChecked()

    /**
     * Gets a shopping item based off the id
     * @param id of the shopping item entity
     * @return shopping item that matches id
     * */
    @Query("SELECT * FROM item_table where id = (:id)")
    fun getItem(id: Int): ShoppingItemEnt

    /**
     * Adds shopping item to the list
     * @param item to insert into the db
     * */
    @Insert
    fun addItem(item: ShoppingItemEnt)

    /**
     * Updates the shopping item in the db
     * @param item to insert
     * */
    @Update
    fun updateItem(item: ShoppingItemEnt)

    /**
     * Sets the shopping item with the id to the check boolean
     * @param id of the shopping item
     * @param check the boolean to set the checked property
     * */
    @Query("UPDATE item_table SET checked = (:check) WHERE id = (:id)")
    fun checkItem(id: Int, check: Boolean)

    /**
     * Delete item from db
     * @param item to delete
     * */
    @Delete
    fun removeItem(item: ShoppingItemEnt)
}