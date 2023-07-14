package com.example.recipeapp.ui.shoppingList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.Util
import com.example.recipeapp.db.entities.ShoppingItemEnt

/**
 * Adapter to display shopping list filtered by categories
 * @author Ariana,Conor,Cordell,Derek
 * @param itemsIn list of shopping items to display
 * @param vm passing in a view model so i can alter it within the adapter
 * */
class CategoryAdapter(
    itemsIn: List<ShoppingItemEnt>,
    private val vm: ShoppingListViewModel
) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    private val items = Util.mapByProp(itemsIn)
    /**
     * Maps items in the recipe row binding to variables in the holder
     * @param itemView the binding of each shopping item
     * */
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Creates a recycler view for each category
         * @param result list of shopping item entities in a category
         * */
        fun bind(result: List<ShoppingItemEnt>) {
            itemView.findViewById<TextView>(R.id.category).text = Util.titleCase(result[0].cat)
            val childAdapter = ShoppingListItemAdapter(result, vm)
            val swipeGesture = object : SwipeGesture() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            childAdapter.deleteItem(viewHolder.adapterPosition)
                        }
                    }
                }


                /**
                 * Changing the colour of a swiped object to red
                 * */
                override fun onSelectedChanged(
                    viewHolder: RecyclerView.ViewHolder?,
                    actionState: Int
                ) {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        viewHolder?.itemView?.findViewById<LinearLayout>(R.id.shopping_list_item_root)?.setBackgroundColor(Color.argb(1.3f,1f,0f,0f))
                    }
                }

                /**
                 * Changes the colour back to white when you release a swipe action
                 * */
                override fun clearView(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder
                ) {
                    viewHolder.itemView.findViewById<LinearLayout>(R.id.shopping_list_item_root)?.setBackgroundResource(R.drawable.shopping_item_corners)
                    super.clearView(recyclerView, viewHolder)
                }
            }
            val recycler = itemView.findViewById<RecyclerView>(R.id.category_recycle)
            recycler.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = childAdapter
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(recycler)
        }
    }

    /**
     * Creates a view holder for each category
     * @param parent the parent view group
     * @param viewType the view type int
     * @return the view holder class
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.shopping_list_category_rv, parent, false)
    )

    /**
     * Binds the view holder for every item in the shopping item list
     * @param holder the view holder to bind
     * @param position the position of the shopping item in the list to bind
     * */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[items.keys.elementAt(position)]?.let { holder.bind(it) }
    }

    /**
     * returns the size of items
     * @return the size
     * */
    override fun getItemCount(): Int {
        return items.size
    }
}