package com.example.recipeapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipeapp.db.entities.ShoppingItemEnt
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Ariana,Conor,Cordell,Derek
 * Test class to test the util class
 * */
@RunWith(AndroidJUnit4::class)
class UtilTest {

    /**
     * Test to make sure any text has title case for every word
     * */
    @Test
    fun titleCase(){
        val title = "Hello There Title Test"
        val nonTitle = "hello there title test"
        assertEquals(title, Util.titleCase(nonTitle))
        val title2 = "Hello There Title Test2 "
        val nonTitle2 = "HeLLo ThErE tiTlE teSt2 "
        assertEquals(title2, Util.titleCase(nonTitle2))
        val title3 = " "
        val nonTitle3 = " "
        assertEquals(title3, Util.titleCase(nonTitle3))

        assertEquals("Hello World", Util.titleCase("hello world"))
        assertEquals("Hello World", Util.titleCase("HELLO WORLD"))
        assertEquals("Hello World", Util.titleCase("hELLO wORLD"))
        assertEquals("Hello World", Util.titleCase("Hello World"))
        assertEquals("Hello World", Util.titleCase("hello WORLD"))
        assertEquals("Hello World", Util.titleCase("Hello world"))
        assertEquals("Hello World", Util.titleCase("hello World"))
    }

    /**
     * Testing the mapByProp which takes a list of shopping item entities and maps them by the category field
     * */
    @Test
    fun mapByProp(){
        val item1 = ShoppingItemEnt(0,"Apples",2.1,"kg",false,"Fruit")
        val item2 = ShoppingItemEnt(1,"Carrots",5.4,"kg",false,"Vege")
        val item3 = ShoppingItemEnt(2,"Pineapple",2.6,"kg",false,"Fruit")
        val item4 = ShoppingItemEnt(3,"Beef",2.1,"kg",false,"Meat")
        val item5 = ShoppingItemEnt(4,"Cheese",2.1,"kg",false,"Vege")
        val itemList: List<ShoppingItemEnt> = listOf(item1,item2,item3,item4,item5)
        val fruits: List<ShoppingItemEnt> = listOf(item1,item3)
        val meats: List<ShoppingItemEnt> = listOf(item4)
        val map = Util.mapByProp(itemList)
        assertEquals(map["fruit"],fruits)
        assertEquals(map["meat"]!!.size,meats.size)
    }

    /**
     * Takes a string and formats it with bullet points and removed square brackets
     * */
    @Test
    fun stringToFormattedList(){
        val test =
            Util.stringToFormattedList("Step 1\\ 1/4kg [whole chicken], cut into pieces or chicken wings\\ 2Tbsp [rice wine]\\ 2tsp minced [ginger]\\ 1tsp fine sea [salt]")
        val expected = "Step 1\n1/4kg whole chicken, cut into pieces or chicken wings\n\n2Tbsp rice wine\n\n2tsp minced ginger\n\n1tsp fine sea salt\n\n"
        assertEquals(test.toString(),expected)
    }

    /**
     * Tests that a string with 4 parts gets turned into a shopping item entity
     * */
    @Test
    fun ingredientsToList(){
        val ing = "1, Whole, pantry, Pie, 2, Kg, Produce, Apples"
        val expectedItem = ShoppingItemEnt(0,"Pie",1.0,"Whole",true,"pantry")
        val list = Util.ingredientsToList(ing)
        assert(list.contains(expectedItem))
    }
}