package com.example.recipeapp.ui.shoppingList

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.recipeapp.db.AppDB
import com.example.recipeapp.db.entities.ShoppingItemEnt
import com.example.recipeapp.db.repos.ShoppingItemRepo

/**
 * shopping list view model to hold all data about the shopping list
 * @author Ariana,Conor,Cordell,Derek
 * @param repo the repo for shopping list data access
 * */
class ShoppingListViewModel(private val repo: ShoppingItemRepo) : ViewModel() {
    var items: LiveData<List<ShoppingItemEnt>> = repo.getShoppingList().asLiveData()

    /**
     * adds an item to the db
     * @param item shopping item to insert
     * */
    fun addItem(item: ShoppingItemEnt) {
        repo.addItem(item)
    }

    /**
     * checks an item and then removes it
     * @param item to remove
     * */
    fun removeItem(item: ShoppingItemEnt) {
        repo.removeItem(item)
    }

    /**
     * sets the check property of the item to the opposite of what it was
     * @param item to set
     * */
    fun checkItem(item: ShoppingItemEnt) {

        item.checked = !item.checked
        repo.checkItem(item.id, item.checked)
    }

    /**
     * uses the repo to get a shopping item by id
     * @param id of shopping item
     * @return shopping item
     * */
    fun getItem(id: Int): ShoppingItemEnt {
        return repo.getItem(id)
    }

    /**
     * removes all checked items in the db
     * */
    fun removeChecked() {
        repo.removedChecked()
    }

    /**
     * merges the current shopping list with the inputted list
     * @param newItems the new items to merge into the shopping list
     * */
    fun mergeItems(newItems: List<ShoppingItemEnt>) {
        val oldItems: HashMap<String, ShoppingItemEnt> =
            items.value!!.associateBy({ it.item.lowercase() }, { it }) as HashMap
        val items = newItems.filter { it.checked }
        items.forEach { newItem ->
            if (oldItems.containsKey(newItem.item.lowercase())) {
                val item = oldItems[newItem.item.lowercase()]!!
                item.amount += newItem.amount
                updateItem(item)
            } else {
                newItem.checked = false
                when (newItem.cat.lowercase().trim()) {
                    "produce" -> newItem.cat = "Produce \uD83C\uDF4E"
                    "meats&seafood" -> newItem.cat = "Meats & Seafood \uD83C\uDF57"
                    "baking goods" -> newItem.cat = "Baking Goods \uD83E\uDDC8"
                    "frozen" -> newItem.cat = "Frozen \uD83C\uDF66"
                    "pantry" -> newItem.cat = "Pantry \uD83C\uDF6B"
                    "bakery" -> newItem.cat = "Bakery \uD83C\uDF5E"
                    "dairy" -> newItem.cat = "Dairy \uD83E\uDD5B"
                }
                addItem(newItem)
            }
        }
    }

    private fun updateItem(item: ShoppingItemEnt) {
        repo.updateItem(item)
    }


    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()
                val repo = ShoppingItemRepo(
                    AppDB.getInstance(application.applicationContext).shoppingItemDAO()
                )

                return ShoppingListViewModel(
                    (repo)
                ) as T
            }
        }
    }

}