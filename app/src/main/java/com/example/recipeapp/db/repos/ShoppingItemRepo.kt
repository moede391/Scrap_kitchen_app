package com.example.recipeapp.db.repos

import androidx.annotation.WorkerThread
import com.example.recipeapp.db.dao.ShoppingItemEntDAO
import com.example.recipeapp.db.entities.ShoppingItemEnt
import kotlinx.coroutines.flow.Flow

/**
 * Shopping item repository
 * @author Ariana,Conor,Cordell,Derek
 * */
class ShoppingItemRepo(private val dao: ShoppingItemEntDAO) {
    private val items: Flow<List<ShoppingItemEnt>> = dao.getShoppingList()

    /**
     * Returns the item property
     * @return list of shopping item entities
     * */
    fun getShoppingList(): Flow<List<ShoppingItemEnt>> {
        return items
    }

    /**
     * uses the dao to removed all checked shopping items
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun removedChecked() {
        dao.removedChecked()
    }

    /**
     * uses the dao to get a shopping item based off an id
     * @param id of the shopping item entity
     * @return the shopping item with the id
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getItem(id: Int): ShoppingItemEnt {
        return dao.getItem(id)
    }

    /**
     * uses the dao to add an item into the db
     * @param item the item to add
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun addItem(item: ShoppingItemEnt) {
        dao.addItem(item)
    }

    /**
     * uses the dao to change the checked property of a shopping item
     * @param id of the shopping item
     * @param check the boolean to set the checked property
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun checkItem(id: Int, check: Boolean) {
        dao.checkItem(id, check)
    }

    /**
     * uses the dao to update an item in the db
     * @param item the updated shopping item
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun updateItem(item: ShoppingItemEnt) {
        dao.updateItem(item)
    }

    /**
     * uses the dao to delete an item in the db
     * @param item the deleted shopping item
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun removeItem(item: ShoppingItemEnt) {
        dao.removeItem(item)
    }
}