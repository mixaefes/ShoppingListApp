package com.example.shoppinglistefes.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistefes.R
import com.example.shoppinglistefes.databinding.FragmentHomeBinding
import com.example.shoppinglistefes.presentation.adapter.OnItemClickListener
import com.example.shoppinglistefes.presentation.adapter.ShoppingItemTouchHelperCallback
import com.example.shoppinglistefes.presentation.adapter.ShoppingListAdapter
import com.example.shoppinglistefes.presentation.viewmodel.PurchaseViewModel
import com.example.shoppinglistefes.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val myViewModel: PurchaseViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var shoppingAdapter: ShoppingListAdapter? = null
    private var itemTouchHelper : ShoppingItemTouchHelperCallback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerId.layoutManager = LinearLayoutManager(context)
        binding.floatingActionButton.setOnClickListener {
            sharedViewModel.select(null)
            it.findNavController().navigate(R.id.action_homeFragment_to_addPurchaseFragment)
        }
        shoppingAdapter = ShoppingListAdapter(this)
        itemTouchHelper = ShoppingItemTouchHelperCallback(adapter = shoppingAdapter!!)
        val toushHelper = ItemTouchHelper(itemTouchHelper!!)
        binding.recyclerId.adapter = shoppingAdapter
        toushHelper.attachToRecyclerView(binding.recyclerId)
        lifecycle.coroutineScope.launch {
            myViewModel.allPurchase.collect() { list ->
                shoppingAdapter?.submitList(list)

            }
        }
        Log.i("HomeFragment", "my list: ${shoppingAdapter!!.currentList}")
        binding.buttonListWasted.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_wastedFragment)
        }
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settingsId -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        shoppingAdapter?.currentList?.get(position)?.let { sharedViewModel.select(it) }
        findNavController().navigate(R.id.action_homeFragment_to_addPurchaseFragment)
    }
}