package com.example.recipeapp.ui.mealPlanner

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipeapp.db.AppDB
import com.example.recipeapp.db.entities.MealPlannerEnt
import com.example.recipeapp.db.repos.MealPlannerRepo
import com.example.recipeapp.getOrAwaitValue
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Ariana,Conor,Cordell,Derek
 * Test class to test meal planner view model
 * */
@RunWith(AndroidJUnit4::class)
class MealPlannerViewModelTest : TestCase(){
    private lateinit var viewModel: MealPlannerViewModel
    private val mealPlannerData = listOf(MealPlannerEnt(0,0), MealPlannerEnt(1,1),MealPlannerEnt(2,2),MealPlannerEnt(3,3),MealPlannerEnt(4,4),MealPlannerEnt(5,5),MealPlannerEnt(6,6))

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    /**
     * Sets up the view model for each test to use it
     * */
    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).allowMainThreadQueries().build()
        val dao = db.mealPlannerDAO()
        dao.setMealPlanner(mealPlannerData)
        viewModel = MealPlannerViewModel(MealPlannerRepo(dao))
    }

    /**
     * Updating the meal planner table with a new recipe id and checking that it set
     * */
    @Test
    fun updateMealItem() {
        viewModel.updateMealItem(15555,0)
        val newEnt = MealPlannerEnt(0,15555)
        val result = viewModel.items.getOrAwaitValue().contains(newEnt)
        assert(result)
    }

}