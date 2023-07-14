package com.example.recipeapp.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.consts.Emojis
import com.example.recipeapp.databinding.RecipeRowBinding
import com.example.recipeapp.db.entities.RecipeEnt

/**
 * An adapter to show a list of recipes
 * @author Ariana,Conor,Cordell,Derek
 * @param recipeList list of recipes to display
 * @param listener class that implements the on recipe selected function
 * */
class RecipeListAdapter(
    private val recipeList: List<RecipeEnt>,
    private val listener: RecipeListFragment
) : RecyclerView.Adapter<RecipeListAdapter.MyViewHolder>() {
    private val emojis = Emojis.Companion

    /**
     * Maps items in the recipe row binding to variables in the holder
     * @param recipeRowBinding the binding of each recipe row
     * @param onRecipeSelected function to call when an onclick is activated
     * */
    inner class MyViewHolder(
        recipeRowBinding: RecipeRowBinding,
        private val onRecipeSelected: OnRecipeSelected
    ) : RecyclerView.ViewHolder(recipeRowBinding.root), View.OnClickListener {
        init {
            recipeRowBinding.root.setOnClickListener(this)
        }
        /**
         * Sets the recipe view model current recipe to the one clicked
         * @param p0 the recipe view that was clicked
         * */
        override fun onClick(p0: View?) {
            onRecipeSelected.onRecipeSelected(recipeList[adapterPosition])
        }

        var title = recipeRowBinding.titleTxt
        var emoji = recipeRowBinding.idTxt

    }

    /**
     * Creates a view holder for the recipe row
     * @param parent the parent view group
     * @param viewType the view type int
     * @return the view holder class
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, listener)
    }

    /**
     * Binds the view holder for every item in the recipe list
     * @param holder the view holder to bind
     * @param position the position of the recipe in the list to bind
     * */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipeList[position]
        val titleEdit = "\n${currentItem.name}\n".replace("(", "\n(")
        holder.title.text = titleEdit
        holder.emoji.text = emojis.getEmoji(currentItem.country)
    }

    /**
     * Gets the size of the recipe list
     * @return the size
     * */
    override fun getItemCount(): Int {
        return recipeList.size
    }

    /**
     * The interface that the listener implements
     * */
    interface OnRecipeSelected {
        /**
         * called when a recipe is selected
         * @param recipe the recipe selected
         * */
        fun onRecipeSelected(recipe: RecipeEnt)
    }
}