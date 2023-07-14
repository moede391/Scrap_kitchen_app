package com.example.recipeapp.consts

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for the emoji class
 * */
class EmojisTest{
    /**
     * Tests the getemoji return the right emoji or a default emoji
     * */
    @Test
    fun getEmoji(){
        assertEquals(Emojis.getEmoji("south korea"),"\uD83C\uDDF0\uD83C\uDDF7")
        assertEquals(Emojis.getEmoji("Nowhereland"),"\uD83C\uDF0E")
    }
}