package com.example.recipeapp.ui.shoppingList

import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R

/**
 * Class to allow swipe actions
 * @author Ariana,Conor,Cordell,Derek
 * */
abstract class SwipeGesture : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    /**
     * handles what happens when you move a view
     * */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        return false
    }
}