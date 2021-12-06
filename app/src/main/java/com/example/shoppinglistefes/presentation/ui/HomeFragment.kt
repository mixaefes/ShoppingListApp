package com.example.shoppinglistefes.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistefes.R
import com.example.shoppinglistefes.ShoppingApplication
import com.example.shoppinglistefes.presentation.adapter.OnItemClickListener
import com.example.shoppinglistefes.presentation.adapter.ShoppingListAdapter
import com.example.shoppinglistefes.databinding.FragmentHomeBinding
import com.example.shoppinglistefes.presentation.viewmodel.PurchaseViewModel
import com.example.shoppinglistefes.presentation.viewmodel.PurchaseViewModelFactory
import com.example.shoppinglistefes.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val myViewModel : PurchaseViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var shoppingAdapter: ShoppingListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerId.layoutManager = LinearLayoutManager(context)
        binding.floatingActionButton.setOnClickListener {
            sharedViewModel.select(null)
            it.findNavController().navigate(R.id.action_homeFragment_to_addPurchaseFragment)
        }
        shoppingAdapter = ShoppingListAdapter(this)
        binding.recyclerId.adapter = shoppingAdapter
        lifecycleScope.launch {
            myViewModel.allPurchase.observe(viewLifecycleOwner, Observer { list ->
                shoppingAdapter?.let {
                    it.submitList(list)
                }
            })
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
        Toast.makeText(
            this.context,
            "clicl click click ${shoppingAdapter?.currentList?.get(position)}",
            Toast.LENGTH_SHORT
        ).show()
        shoppingAdapter?.currentList?.get(position)?.let { sharedViewModel.select(it) }
        findNavController().navigate(R.id.action_homeFragment_to_addPurchaseFragment)
    }
}