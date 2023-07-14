package com.example.recipeapp.ui.shoppingList

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentShoppingListBinding
import kotlinx.coroutines.launch

/**
 * Fragment to show a list of shopping items
 * @author Ariana,Conor,Cordell,Derek
 * */
class ShoppingListFragment : Fragment() {

    private lateinit var binding: FragmentShoppingListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter


    private val shoppingListViewModel: ShoppingListViewModel by activityViewModels { ShoppingListViewModel.Factory }

    /**
     * creates a binding and binds the recycler view
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentShoppingListBinding.inflate(inflater)
        binding = fragmentBinding
        recyclerView = binding.shoppingListRecycle
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        shoppingListViewModel.items.observe(viewLifecycleOwner) {
            val recyclerViewState : Parcelable = recyclerView.layoutManager!!.onSaveInstanceState()!!
            adapter = CategoryAdapter(it, shoppingListViewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager!!.onRestoreInstanceState(recyclerViewState)
            if(it.isEmpty()){
                binding.Empty.visibility = View.VISIBLE
            } else{
                binding.Empty.visibility = View.GONE
            }
        }
        binding.addShoppingItem.setOnClickListener {
            findNavController().navigate(R.id.action_shoppingListFragment_to_addShoppingItemFragment)
        }
        return fragmentBinding.root
    }

    /**
     * removes all checked shopping items when you change fragments
     * */
    override fun onPause() {
        super.onPause()
        lifecycle.coroutineScope.launch {
            shoppingListViewModel.removeChecked()
        }
    }
}