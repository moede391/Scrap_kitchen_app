package com.example.recipeapp.ui.shoppingList

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipeapp.db.AppDB
import com.example.recipeapp.db.entities.ShoppingItemEnt
import com.example.recipeapp.db.repos.ShoppingItemRepo
import com.example.recipeapp.getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Ariana,Conor,Cordell,Derek
 * Test class to test shopping list view model
 * */
@RunWith(AndroidJUnit4::class)
class ShoppingListViewModelTest {

    private lateinit var viewModel: ShoppingListViewModel
    private var testShoppingItem = ShoppingItemEnt(0,"Apples",2.0,"kg",false,"Fruit")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    /**
     * Sets up the view model for each test to use it
     * */
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).allowMainThreadQueries().build()
        val dao = db.shoppingItemDAO()
        dao.addItem(testShoppingItem)

        viewModel = ShoppingListViewModel(ShoppingItemRepo(dao))
    }

    /**
     * Adding a item to the shopping list and checking it is in the items list
     * */
    @Test
    fun addItem() {
        val item = ShoppingItemEnt(0,"Cheese",2.0,"kg",false,"Dairy")
        viewModel.addItem(item)
        val result = viewModel.items.getOrAwaitValue().find { it.item == item.item }
        assert(result != null)
    }

    /**
     * Testing the get item
     * */
    @Test
    fun getItem(){
        val item = viewModel.items.getOrAwaitValue().find { it.item == testShoppingItem.item }
        assert(item != null)
    }

    /**
     * Adding and removing the item and making sure it was removed
     * */
    @Test
    fun removeItem() {
        val item = viewModel.items.getOrAwaitValue().find { it.item == testShoppingItem.item }!!
        viewModel.removeItem(item)
        val items = viewModel.getItem(item.id)
        assert(items == null)
    }

    /**
     * Changing the checked property of an item and making sure it changed
     * */
    @Test
    fun checkItem() {
        val item = viewModel.items.getOrAwaitValue().find { it.item == testShoppingItem.item }!!
        viewModel.checkItem(item)
        val items = viewModel.items.getOrAwaitValue().find { it.item == testShoppingItem.item }!!
        assert(items.checked)
    }

    /**
     * Making sure the checked item is properly removed
     * */
    @Test
    fun removeChecked() {
        val newItem = ShoppingItemEnt(0,"Cheese",2.0,"kg",true,"Dairy")
        viewModel.addItem(newItem)
        viewModel.removeChecked()
        val items = viewModel.items.getOrAwaitValue()
        val item = viewModel.items.getOrAwaitValue().find { it.item == newItem.item }
        assert(!items.contains(item))
    }

    /**
     * Testing the merge function that merges current shopping list items with the new ones
     * */
    @Test
    fun mergeItems() {
        val itemsToMerge : List<ShoppingItemEnt> = listOf(ShoppingItemEnt(0,"Apples",3.0,"kg",true,"Fruit"),ShoppingItemEnt(0,"Carrots",3.0,"kg",true,"Produce"),ShoppingItemEnt(0,"Banana",3.0,"kg",true,"meats&seafood"),ShoppingItemEnt(0,"flour",3.0,"kg",true,"baking goods"),ShoppingItemEnt(0,"ice cream",3.0,"kg",true,"frozen"),ShoppingItemEnt(0,"bread",3.0,"kg",true,"bakery"),ShoppingItemEnt(0,"Milk",3.0,"kg",true,"dairy"),ShoppingItemEnt(0,"Choc",3.0,"kg",true,"pantry"))
        val expectedAmount = 5.0
        val items = viewModel.items.getOrAwaitValue()
        viewModel.mergeItems(itemsToMerge)

        val fetchItem = viewModel.items.getOrAwaitValue().find{
            it.item == itemsToMerge[0].item && it.cat == itemsToMerge[0].cat && it.metric == itemsToMerge[0].metric
        }
        assert(fetchItem!!.amount == expectedAmount)

    }
}