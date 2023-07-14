package com.example.recipeapp.ui.shoppingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.Util
import com.example.recipeapp.db.entities.ShoppingItemEnt
import kotlin.math.floor

/**
 * adapter to show a list of shopping items
 * @author Ariana,Conor,Cordell,Derek
 * @param shoppingList the list of shopping items to display
 * @param vm a view model to alter within the adapter
 * */
class ShoppingListItemAdapter(
    private val shoppingList: List<ShoppingItemEnt>,
    private val vm: ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingListItemAdapter.MyViewHolder>() {

    /**
     * Maps items in the shopping list row binding to variables in the holder
     * @param view the binding of each shopping item
     * */
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.findViewById<CheckBox>(R.id.s_got).setOnClickListener(this)
        }

//        val name: TextView = view.findViewById(R.id.slist_name)
        val metric: TextView = view.findViewById(R.id.slist_amount_metric)
        val checkbox: CheckBox = view.findViewById(R.id.s_got)
        /**
         * Checks the item that was clicked
         * @param view the shopping list view that was clicked
         * */
        override fun onClick(view: View) {
            val item = shoppingList[adapterPosition]
            vm.checkItem(item)
        }
    }

    /**
     * Creates a view holder with the layout shopping list rv
     * @param parent parent view group
     * @param viewType view type int
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_rv, parent, false)
        return MyViewHolder(binding)
    }

    /**
     * returns the size of the shopping list
     * @return the size
     * */
    override fun getItemCount(): Int {
        return shoppingList.size
    }

    /**
     * Gets the shopping list property
     * @return list of shopping item entities
     * */
    fun getItems(): List<ShoppingItemEnt> {
        return shoppingList
    }

    /**
     * Uses the view model to delete the item
     * @param i the index of the shopping list item
     * */
    fun deleteItem(i: Int) {
        vm.removeItem(shoppingList[i])
    }

    /**
     * binds the view holder with each item
     * @param holder the view holder
     * @param position the position of the item to bind
     * */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = shoppingList[position]
//        holder.name.text = Util.titleCase(item.item)
        holder.checkbox.isChecked = item.checked
        holder.checkbox.text = Util.titleCase(item.item)
        var amount = item.amount.toString()
        if (item.amount == floor(item.amount)) {
            amount = item.amount.toInt().toString()
        }
        val text = amount + " " + item.metric
        holder.metric.text = text
    }
}